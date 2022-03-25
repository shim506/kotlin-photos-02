package com.example.kotlinphotos.photos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R

class DoodleAdapter(
    diffCallback: DiffUtil.ItemCallback<Photo>,
    private val imageSelectedListener: ImageSelectedListener
) :
    ListAdapter<Photo, DoodleAdapter.DoodleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoodleViewHolder {
        return DoodleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DoodleViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener {
            holder.checkBox.isChecked = true
            holder.checkBox.visibility = View.VISIBLE
            imageSelectedListener.onEvent()
            return@setOnLongClickListener true
        }
    }

    open class DoodleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photoImageView = view.findViewById<ImageView>(R.id.view_photo)
        val checkBox: CheckBox = view.findViewById<CheckBox>(R.id.checkbox_select)

        fun bind(photo: Photo) {
            photoImageView.setImageBitmap(photo.bitmap)
            when (photo.viewType) {
                ViewType.READ -> {
                    checkBox.visibility = View.INVISIBLE
                }
                ViewType.EDIT -> {
                    checkBox.visibility = View.VISIBLE
                }
            }
        }
    }


}