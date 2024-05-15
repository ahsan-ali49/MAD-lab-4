package com.example.mad_lab_4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class Task4 : ComponentActivity() {
    private var imagesList = mutableListOf<File>()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            loadImages()
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(imagesList) { file ->
                    // Display each image, you might want to use Accompanist's Coil to load images
                }
            }
        }

        checkPermissionAndLoadImages()
    }

    private fun checkPermissionAndLoadImages() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImages()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun loadImages() {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        lifecycleScope.launch {
            imagesList = getImagesFromDirectory(path)
            // Trigger the UI to update, for example by setting a LiveData or State in Compose
        }
    }

    private suspend fun getImagesFromDirectory(directory: File): MutableList<File> = withContext(Dispatchers.IO) {
        directory.listFiles { _, name -> name.endsWith(".jpg") || name.endsWith(".png") }?.toMutableList() ?: mutableListOf()
    }
}
