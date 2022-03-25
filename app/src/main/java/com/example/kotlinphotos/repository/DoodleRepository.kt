package com.example.kotlinphotos.repository

import com.example.kotlinphotos.model.Photo

class DoodleRepository(
    private val doodleAssetDataSource: DoodleAssetDataSource
) {
    suspend fun loadDoodlePhotos(): List<Photo> {
        return doodleAssetDataSource.getDoodleData()
    }
}