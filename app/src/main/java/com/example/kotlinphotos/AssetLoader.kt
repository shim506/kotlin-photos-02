package com.example.kotlinphotos

import android.content.Context

class AssetLoader(private val context: Context) {

    private fun loadAsset(fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            String(byteArray)
        }
    }

    fun jsonToString(fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }
}