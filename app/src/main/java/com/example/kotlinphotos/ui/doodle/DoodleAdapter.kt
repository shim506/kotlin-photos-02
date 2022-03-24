package com.example.kotlinphotos.ui.doodle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import com.example.kotlinphotos.model.Photo

class DoodleAdapter(
    diffCallback: DiffUtil.ItemCallback<Photo>,
    private val saveListener: OnSaveListener
) : ListAdapter<Photo, DoodleAdapter.DoodleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleViewHolder {
        return DoodleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DoodleViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            for (current in currentList) {
                current.mode = 1
            }
            false
        }
        holder.itemView.setOnClickListener {
            if (photo.mode == 1) {
                currentList[position].isChecked = true
                saveListener.save()
                notifyDataSetChanged()
            }
        }
    }

    inner class DoodleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val photoImageView = view.findViewById<ImageView>(R.id.view_photo)
        private val photoCheckBox = view.findViewById<CheckBox>(R.id.checkbox_select)

        fun bind(photo: Photo) {
            when (photo.mode) {
                1 -> photoCheckBox.visibility = View.VISIBLE
                0 -> photoCheckBox.visibility = View.INVISIBLE
            }
            photoCheckBox.setOnCheckedChangeListener(null)
            photoCheckBox.isChecked = photo.isChecked
            photoCheckBox.setOnCheckedChangeListener { _, value -> photo.isChecked = value }
            photoImageView.setImageBitmap(photo.bitmap)
        }
    }
}