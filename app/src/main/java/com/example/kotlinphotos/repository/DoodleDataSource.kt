package com.example.kotlinphotos.repository

import com.example.kotlinphotos.model.Photo

interface DoodleDataSource {

    fun getDoodleData(): List<Photo>
}