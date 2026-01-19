package com.example.myvehicles

import androidx.compose.runtime.Composable
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

class IOSFilePicker : FilePicker {
    override fun pickImage(onResult: (String?) -> Unit) {}
    override fun pickFile(onResult: (String?) -> Unit) {}
}

@Composable
actual fun getFilePicker(): FilePicker = IOSFilePicker()

actual fun openFilePlatform(path: String?) {
    // Κενό για το iOS build
}