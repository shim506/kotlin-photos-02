package com.example.kotlinphotos.ui.doodle

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinphotos.AssetLoader
import com.example.kotlinphotos.repository.DoodleAssetDataSource
import com.example.kotlinphotos.repository.DoodleRepository

class DoodleViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DoodleViewModel::class.java)) {
            val doodleRepository = DoodleRepository(DoodleAssetDataSource(AssetLoader(context)))
            DoodleViewModel(doodleRepository) as T
        } else {
            throw IllegalArgumentException("ERROR")
        }
    }

}
