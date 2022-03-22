package com.example.kotlinphotos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

const val PERMISSIONS_REQUEST_READ_STORAGE = 321

class SecondActivity : AppCompatActivity() {
    val TAG = "SecondActivity"

    private lateinit var albumOpenConstraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        albumOpenConstraintLayout =
            findViewById<ConstraintLayout>(R.id.open_album_constraint_layout)
        setAlbumOpenConstraintLayoutListener()

    }

    private fun setAlbumOpenConstraintLayoutListener() {
        albumOpenConstraintLayout.setOnClickListener {
            openAlbum()
        }
    }

    private fun openAlbum() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "존재")
        } else {
            Log.d(TAG, "미존재")
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            Log.d(TAG, "이후 거부")
            Snackbar.make(
                albumOpenConstraintLayout,
                "설정창에서 해당 앱에 대한 승인을 해주세요",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_READ_STORAGE);
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG , "승인")
            } else {
                Log.d(TAG, "거부")
            }
        }
    }
}