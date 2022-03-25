package com.example.kotlinphotos.repository

import com.example.kotlinphotos.model.Photo

class DoodleRepository(
    private val doodleAssetDataSource: DoodleAssetDataSource
) {
    fun loadDoodlePhotos(): List<Photo> {
        return doodleAssetDataSource.getDoodleData()
    }
}