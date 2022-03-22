package com.example.kotlinphotos.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import org.json.JSONObject

class DoodleActivity : AppCompatActivity() {
    lateinit var backButton: ImageButton
    lateinit var recyclerView: RecyclerView

    val TAG = "DoodleActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        backButton = findViewById(R.id.image_button_back)
        backButton.setOnClickListener { finish() }

        val str = AssetLoader.jsonToString(this, DOODLE_FILE_NAME)
        Log.d(TAG, str?:"")
    }

    companion object {
        const val DOODLE_FILE_NAME = "doodle.json"
    }
}