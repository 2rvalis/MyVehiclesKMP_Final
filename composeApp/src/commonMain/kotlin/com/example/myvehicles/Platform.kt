package com.example.myvehicles

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

interface FilePicker {
    fun pickImage(onResult: (String?) -> Unit)
    fun pickFile(onResult: (String?) -> Unit)
}

@Composable
expect fun getFilePicker(): FilePicker

expect fun openFilePlatform(path: String?)