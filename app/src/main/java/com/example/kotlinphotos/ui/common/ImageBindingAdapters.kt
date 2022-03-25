package com.example.kotlinphotos.ui.common


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


@BindingAdapter("imageUrl")
suspend fun loadImage(view: ImageView, imageUrl: String) {
    imageUrl.let{
        val bitmap = withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val url = URL(imageUrl)
                val stream = url.openStream()
                BitmapFactory.decodeStream(stream)
            }.getOrNull()
        }
        view.setImageBitmap(bitmap)
    }
}
//    view.setImageBitmap()
//    Glide.with(view)
//        .load(imageUrl)
//        .into(view)


//    view.setImageBitmap(bitmap)
