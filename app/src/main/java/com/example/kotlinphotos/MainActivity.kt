package com.example.kotlinphotos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_photos)
        val randomColors = mutableListOf<Int>()
        repeat(40) {
            randomColors.add(createRandomColor())
        }

        val adaptor = PhotosAdaptor(randomColors, PhotosDiffCallback())
        recyclerView.adapter = adaptor
        recyclerView.layoutManager = GridLayoutManager(this, 4)
    }

    private fun createRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }
}