package com.example.kotlinphotos.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R

class DoodleAdapter(diffCallback: DiffUtil.ItemCallback<Photo>) :
    ListAdapter<Photo, DoodleAdapter.DoodleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleViewHolder {
        return DoodleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: DoodleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DoodleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val photoImageView = view.findViewById<ImageView>(R.id.view_photo)

        fun bind(photo: Photo) {
            photoImageView.setImageBitmap(photo.bitmap)
        }
    }
}