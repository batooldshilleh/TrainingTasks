package com.example.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.tasks.model.PhotoModel
import com.example.tasks.R

class PhotoAdapter(
    private val photoList: MutableList<PhotoModel>,
    val  onDeleteClick: (position: Int) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
   // constructor(photoList:MutableList<PhotoModel>, rec: (position: Int) -> Unit) : this()

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = photoList[position]
        holder.tvName.text = currentItem.name
        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .circleCrop()
            .override(500,500)
            .into(holder.imageView)

        holder.btnDelete.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount() = photoList.size

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val btnDelete: ImageView = itemView.findViewById(R.id.button2)
    }
}
