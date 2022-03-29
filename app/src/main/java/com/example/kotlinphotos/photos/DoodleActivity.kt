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

        CoroutineScope(Dispatchers.IO).launch {
            val str = AssetLoader.jsonToString(applicationContext, DOODLE_FILE_NAME)
            val jsonArray = JSONArray(str)
            val start = System.currentTimeMillis();
            val job = launch {
                repeat(jsonArray.length()) {
                    val json = jsonArray.getJSONObject(it)
                    val title = json.getString("title")
                    val image = json.getString("image")
                    val date = json.getString("date")

                    launch(Dispatchers.IO) {
                        val bitmap = loadImage(image)
                        photos.add(Photo(title, bitmap, date))
                    }
                }
            }
            job.join()
            val end = System.currentTimeMillis()
            val time = (end - start) / 1000
            Log.d(TAG, "사진 개수 : ${photos.size.toString()}")
            Log.d(TAG, "쇼요시간  : $time")
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