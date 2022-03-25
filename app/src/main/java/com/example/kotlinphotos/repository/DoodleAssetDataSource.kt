package com.example.kotlinphotos.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.kotlinphotos.AssetLoader
import com.example.kotlinphotos.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL

class DoodleAssetDataSource(
    private val assetLoader: AssetLoader
) : DoodleDataSource {

    override fun getDoodleData(): List<Photo> {
        val photos = mutableListOf<Photo>()
        val str = assetLoader.jsonToString(DOODLE_FILE_NAME)
        val jsonArray = JSONArray(str)

        val size = jsonArray.length()
        repeat(size) {
            val json = jsonArray.getJSONObject(it)
            val title = json.getString("title")
            val image = json.getString("image")
            val date = json.getString("date")
//            val bitmap = loadImage(image)
            photos.add(Photo(title, image, date))
        }
        return photos
    }

    private fun loadImage(imageUrl: String): Bitmap? {
//        withContext(Dispatchers.IO) {
        return kotlin.runCatching {
                val url = URL(imageUrl)
                val stream = url.openStream()
                BitmapFactory.decodeStream(stream)
            }.getOrNull()
//        }
    }

    companion object {
        const val DOODLE_FILE_NAME = "doodle.json"
    }
}