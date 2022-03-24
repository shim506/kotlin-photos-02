package com.example.kotlinphotos.model

import android.graphics.Bitmap

data class Photo(
    val tittle: String,
    val bitmap: Bitmap?,
    val date: String,
    var isChecked: Boolean = false,
    var mode: Int = 0
)