package com.example.myvehicles

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

// Διεπαφή για επιλογή αρχείων
interface FilePicker {
    fun pickImage(onResult: (String?) -> Unit)
    fun pickFile(onResult: (String?) -> Unit)
}

// Χρησιμοποιούμε @Composable για να έχουμε πρόσβαση στο Context στο Android
@Composable
expect fun getFilePicker(): FilePicker

expect fun openFilePlatform(path: String?)