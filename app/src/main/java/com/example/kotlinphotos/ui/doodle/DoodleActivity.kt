package com.example.kotlinphotos.ui.doodle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.AssetLoader
import com.example.kotlinphotos.R
import com.example.kotlinphotos.model.Photo
import com.example.kotlinphotos.ui.common.PhotosDiffCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

class DoodleActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var saveImageButton: ImageButton
    private val photos = mutableListOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        backButton = findViewById(R.id.image_button_back)
        recyclerView = findViewById(R.id.recyclerview_doodle)
        saveImageButton = findViewById(R.id.image_button_save)

        backButton.setOnClickListener { finish() }
        val doodleAdapter = DoodleAdapter(PhotosDiffCallback(), object : OnSaveListener {
            override fun save() {
                saveImageButton.visibility = View.VISIBLE
            }
        })
        recyclerView.adapter = doodleAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        CoroutineScope(Dispatchers.Main).launch {
            val str = AssetLoader.jsonToString(applicationContext, DOODLE_FILE_NAME)
            val jsonArray = JSONArray(str)

            val size = jsonArray.length()
            repeat(size) {
                val json = jsonArray.getJSONObject(it)
                val title = json.getString("title")
                val image = json.getString("image")
                val date = json.getString("date")
                val bitmap = withContext(Dispatchers.IO) { loadImage(image) }
                photos.add(Photo(title, bitmap, date))
            }
            doodleAdapter.submitList(photos)
        }
    }

    private suspend fun loadImage(imageUrl: String): Bitmap? {
        val url = URL(imageUrl)
        return kotlin.runCatching {
            val stream = url.openStream()
            BitmapFactory.decodeStream(stream)
        }.getOrNull()
    }

    companion object {
        const val DOODLE_FILE_NAME = "doodle.json"
    }
}