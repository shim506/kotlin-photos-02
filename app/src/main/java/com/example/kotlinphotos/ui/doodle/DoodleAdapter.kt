package com.example.kotlinphotos.ui.doodle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import com.example.kotlinphotos.model.Photo
import com.example.kotlinphotos.model.Type.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

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
                current.mode = EDIT
            }
            false
        }
        holder.itemView.setOnClickListener {
            if (photo.mode == EDIT) {
                currentList[position].isChecked = true
                saveListener.showSaveButton()
                notifyDataSetChanged()
            }
        }
    }

    inner class DoodleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val photoImageView = view.findViewById<ImageView>(R.id.view_photo)
        private val photoCheckBox = view.findViewById<CheckBox>(R.id.checkbox_select)

        fun bind(photo: Photo) {
            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = loadImage(photo.imageUrl)
                photoImageView.setImageBitmap(bitmap)
                when (photo.mode) {
                    EDIT -> photoCheckBox.visibility = View.VISIBLE
                    READ -> photoCheckBox.visibility = View.INVISIBLE
                }
                photoCheckBox.setOnCheckedChangeListener(null)
                photoCheckBox.isChecked = photo.isChecked
                photoCheckBox.setOnCheckedChangeListener { _, value -> photo.isChecked = value }
            }
        }

        private suspend fun loadImage(imageUrl: String): Bitmap? {
            return withContext(Dispatchers.IO) {
                kotlin.runCatching {
                    val url = URL(imageUrl)
                    val stream = url.openStream()
                    BitmapFactory.decodeStream(stream)
                }.getOrNull()
            }
        }
    }
}