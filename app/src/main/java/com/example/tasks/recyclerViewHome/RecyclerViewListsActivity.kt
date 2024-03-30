package com.example.tasks.recyclerViewHome

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks.R
import com.example.tasks.adapter.PhotoAdapter
import com.example.tasks.databinding.ActivityRecyclerViewListsBinding
import com.example.tasks.model.PhotoModel
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Suppress("DEPRECATION")
class RecyclerViewListsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewListsBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val photoList = mutableListOf<PhotoModel>()
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null

    private val _IMAGE_REQUEST = 1
    private val _PERMISSION_REQUEST = 101
    private var permissionDeniedCount = 0

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val permissionGranted = permissions.all { it.value }
            if (!permissionGranted) {
                permissionDeniedCount++
                if (permissionDeniedCount >= 2) {
                    showPermissionDialog()
                }
            } else {
                startCamera()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRecyclerViewListsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeRecyclerView()
        setupOnClickListener()

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun initializeRecyclerView() {
        photoAdapter = PhotoAdapter(photoList, ::onDeleteClickFun)

        binding.rvImages.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewListsActivity)
            adapter = photoAdapter
        }
    }

    private fun setupOnClickListener() {
        binding.btnNewImage.setOnClickListener {
            binding.etImageName.text.toString().trim()
            showChooseImageSourceDialog()
        }

        binding.btnCapture.setOnClickListener {
            captureImage()
        }
    }

    private fun startCamera() {
        setupVisibilityCameraStart()
        cameraProviderFutureListener()
    }

    private fun setupVisibilityCameraStart() {
        binding.viewFinder.visibility = View.VISIBLE
        binding.btnCapture.visibility = View.VISIBLE
        binding.btnNewImage.visibility = View.GONE
        binding.etImageName.visibility = View.GONE
        binding.rvImages.visibility = View.GONE
    }

    private fun cameraProviderFutureListener() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e(TAG, R.string.camera_ex.toString(), exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun onDeleteClickFun(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.delete_title)
        builder.setMessage(R.string.delete_Message)
        builder.setPositiveButton(R.string.yes) { dialog, _ ->
            photoList.removeAt(position)
            photoAdapter.notifyItemRemoved(position)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showChooseImageSourceDialog() {
        val options = arrayOf<CharSequence>(
            getString(R.string.gallery),
            getString(R.string.camera)
        )
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.camera_title)
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            activityResultLauncher.launch(REQUIRED_PERMISSIONS)
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, R.string.select_picture.toString()), _IMAGE_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == _IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUrl = data.data.toString()
            val name = binding.etImageName.text.toString()
            addPhoto(name, imageUrl)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addPhoto(name: String, imageUrl: String) {
        val photo = PhotoModel(name, imageUrl)
        photoList.add(photo)
        photoAdapter.notifyDataSetChanged()
        binding.rvImages.scrollToPosition(photoList.size - 1)
        binding.etImageName.text.clear()
    }

    private fun captureImage() {
        val imageCapture = imageCapture ?: return

        val photoFile = File(externalMediaDirs.firstOrNull(), "${System.currentTimeMillis()}.jpg")

        val metadata = ImageCapture.Metadata().apply {
            isReversedHorizontal = false
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
            .setMetadata(metadata)
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val name = binding.etImageName.text.toString()
                    addPhoto(name, savedUri.toString())
                    setupVisibilityCloasCamera()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Image capture failed: ${exception.message}", exception)
                }
            })
    }

    private fun setupVisibilityCloasCamera() {
        binding.viewFinder.visibility = View.GONE
        binding.btnCapture.visibility = View.GONE
        binding.rvImages.visibility = View.VISIBLE
        binding.etImageName.visibility = View.VISIBLE
        binding.btnNewImage.visibility = View.VISIBLE
        binding.rvImages.visibility = View.VISIBLE
    }

    private fun showPermissionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.permission_dialog_title)
        builder.setMessage(R.string.permission_dialog_message)
        builder.setPositiveButton(R.string.settings) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivityForResult(intent, _PERMISSION_REQUEST)
        }
        builder.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    companion object {
        private const val TAG = "CameraXApp"
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}
