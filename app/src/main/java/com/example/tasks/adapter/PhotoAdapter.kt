package com.example.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tasks.R
import com.example.tasks.model.PhotoModel

class PhotoAdapter(
    private var photoList: MutableList<PhotoModel>,
    private val onDeleteClick: (position: Int) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val btnDelete: ImageView = itemView.findViewById(R.id.button2)

        init {
            btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(position)
                }
            }
        }
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
            .override(500, 500)
            .into(holder.imageView)
    }

    override fun getItemCount() = photoList.size

    fun updateList(newList: List<PhotoModel>) {
        val diffResult = DiffUtil.calculateDiff(PhotoDiffCallback(photoList, newList))
        photoList.clear()
        photoList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    private class PhotoDiffCallback(
        private val oldList: List<PhotoModel>,
        private val newList: List<PhotoModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
