package com.example.kotlinphotos.ui.doodle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import com.example.kotlinphotos.databinding.ActivityDoodleBinding
import com.example.kotlinphotos.databinding.ItemBinding
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
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DoodleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DoodleViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            CoroutineScope(Dispatchers.Main).launch {
                with(binding) {
                    binding.photo = photo
                    when (photo.mode) {
                        EDIT -> checkboxSelect.visibility = View.VISIBLE
                        READ -> checkboxSelect.visibility = View.INVISIBLE
                    }
                    checkboxSelect.setOnCheckedChangeListener(null)
                    checkboxSelect.isChecked = photo.isChecked
                    checkboxSelect.setOnCheckedChangeListener { _, value ->
                        photo.isChecked = value
                    }
                }
            }
            itemView.setOnLongClickListener {
                for (current in currentList) {
                    current.mode = EDIT
                }
                false
            }
            itemView.setOnClickListener {
                if (photo.mode == EDIT) {
                    photo.isChecked = true
                    saveListener.showSaveButton()
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}