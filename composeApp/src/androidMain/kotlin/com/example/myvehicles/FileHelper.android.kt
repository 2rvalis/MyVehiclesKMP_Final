package com.example.myvehicles

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object FileHelper {
    actual fun saveToInternalStorage(context: Any, uri: Any, fileName: String): String? {
        val androidContext = context as Context
        val androidUri = uri as Uri

        return try {
            val inputStream = androidContext.contentResolver.openInputStream(androidUri)
            val extension = androidContext.contentResolver.getType(androidUri)
                ?.substringAfterLast("/") ?: "jpg"

            val file = File(androidContext.filesDir, "$fileName.$extension")
            val outputStream = FileOutputStream(file)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}