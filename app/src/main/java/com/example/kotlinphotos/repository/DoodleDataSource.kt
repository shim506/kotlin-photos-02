package com.example.kotlinphotos.repository

import com.example.kotlinphotos.model.Photo

interface DoodleDataSource {

    suspend fun getDoodleData(): List<Photo>
}