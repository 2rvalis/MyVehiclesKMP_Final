package com.example.myvehicles

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object FileHelper {
    fun saveToInternalStorage(context: Any, uri: Any, fileName: String): String?
}