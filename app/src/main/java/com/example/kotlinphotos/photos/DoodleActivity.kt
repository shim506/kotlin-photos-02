package com.example.kotlinphotos.photos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinphotos.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class DoodleActivity : AppCompatActivity() {
    lateinit var backButton: ImageButton
    lateinit var recyclerView: RecyclerView
    val photos = mutableListOf<Photo>()
    val TAG = "DoodleActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doodle)

        backButton = findViewById(R.id.image_button_back)
        backButton.setOnClickListener { finish() }

        CoroutineScope(Dispatchers.Main).launch {
            val str = AssetLoader.jsonToString(applicationContext, DOODLE_FILE_NAME)
            val jsonArray = JSONArray(str)

            repeat(jsonArray.length()) {
                val json = jsonArray.getJSONObject(it)
                val title = json.getString("title")
                val image = json.getString("image")
                val date = json.getString("date")

                val bitmap = withContext(Dispatchers.IO) {
                    loadImage(image)
                }
                photos.add(Photo(title, bitmap, date))
            }
            Log.d(TAG, photos.size.toString())
        }
    }

    suspend fun loadImage(imageUrl: String): Bitmap? {
        val url = URL(imageUrl)
        return kotlin.runCatching {
            val stream = url.openStream()
            BitmapFactory.decodeStream(stream)
        }.getOrNull()
    }

    companion object {
        const val DOODLE_FILE_NAME = "doodle.json"
        val errorIndexList = listOf<Int>(19, 68, 73, 93)
    }
}