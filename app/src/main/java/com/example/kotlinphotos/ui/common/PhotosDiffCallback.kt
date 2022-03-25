package com.example.kotlinphotos.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinphotos.model.Photo


class PhotosDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}