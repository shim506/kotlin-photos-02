package com.example.kotlinphotos

import android.content.Context

object AssetLoader {

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            String(byteArray)
        }
    }

    fun jsonToString(context: Context, fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }
}