package com.example.kotlinphotos.photos

import android.graphics.Bitmap

data class Photo(
    val tittle: String,
    val bitmap: Bitmap?,
    val date: String,
    var viewType: ViewType = ViewType.READ
)

enum class ViewType(val type: Int) {
    READ(1), EDIT(2);
}