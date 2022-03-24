package com.example.kotlinphotos.ui.doodle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import com.example.kotlinphotos.ui.common.PhotosDiffCallback

class DoodleActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var saveImageButton: ImageButton
    private val viewModel: DoodleViewModel by viewModels { DoodleViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        backButton = findViewById(R.id.image_button_back)
        recyclerView = findViewById(R.id.recyclerview_doodle)
        saveImageButton = findViewById(R.id.image_button_save)

        backButton.setOnClickListener { finish() }

        val doodleAdapter = DoodleAdapter(PhotosDiffCallback(), object : OnSaveListener {
            override fun showSaveButton() {
                saveImageButton.visibility = View.VISIBLE
            }
        })
        recyclerView.adapter = doodleAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        viewModel.photos.observe(this) { doodleAdapter.submitList(it) }
    }
}