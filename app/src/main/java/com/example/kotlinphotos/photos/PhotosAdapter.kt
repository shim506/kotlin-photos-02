package com.example.kotlinphotos.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R

class PhotosAdapter(
    private val photos: List<Photo>,
    diffCallback: DiffUtil.ItemCallback<Photo>
) : ListAdapter<Photo, PhotosAdapter.Holder>(diffCallback) {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView.findViewById<View>(R.id.view_photo)
        fun bind(photo: Photo) {
            // view.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}
