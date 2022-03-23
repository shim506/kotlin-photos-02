package com.example.kotlinphotos.photos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R

class PhotoActivity : AppCompatActivity() {
    private lateinit var plusImageButton: ImageButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        plusImageButton = findViewById<ImageButton>(R.id.image_button_plus)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_images)
        val photos = mutableListOf<Photo>()

        recyclerView.adapter = PhotosAdapter(photos, PhotosDiffCallback())
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        setPlusImageButtonListener()
    }

    private fun setPlusImageButtonListener() {
        plusImageButton.setOnClickListener {
            startActivity(Intent(this, DoodleActivity::class.java))
        }
    }
}