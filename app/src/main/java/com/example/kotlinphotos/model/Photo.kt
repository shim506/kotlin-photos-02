package com.example.kotlinphotos.model

import android.graphics.Bitmap
import com.example.kotlinphotos.model.Type.*

data class Photo(
    val tittle: String,
//    val bitmap: Bitmap?,
    val imageUrl: String,
    val date: String,
    var isChecked: Boolean = false,
    var mode: Type = READ
)

enum class Type(val mode: Int) {
    READ(1),
    EDIT(2)
}