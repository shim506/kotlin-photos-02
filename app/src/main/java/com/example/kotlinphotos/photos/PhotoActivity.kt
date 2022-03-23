package com.example.kotlinphotos.photos

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R

class PhotoActivity : AppCompatActivity() {
    val TAG = "PhotoActivity"
    private lateinit var plusImageButton: ImageButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        plusImageButton = findViewById<ImageButton>(R.id.image_button_plus)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview_images)
        val photos = mutableListOf<Photo>()
        //val example = BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_add_24)

        recyclerView.adapter = PhotosAdaptor(photos, PhotosDiffCallback())
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        setPlusImageButtonListener()
    }

    private fun setPlusImageButtonListener() {
        plusImageButton.setOnClickListener {
            startActivity(Intent(this, DoodleActivity::class.java))
        }
    }
}