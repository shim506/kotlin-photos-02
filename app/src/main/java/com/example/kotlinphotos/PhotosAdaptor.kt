package com.example.kotlinphotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PhotosAdaptor(
    private val colors: List<Int>,
    diffCallback: DiffUtil.ItemCallback<Int>
) : ListAdapter<Int, PhotosAdaptor.Holder>(diffCallback) {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView.findViewById<View>(R.id.view_photo)
        fun bind(color: Int) {
            view.setBackgroundColor(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
