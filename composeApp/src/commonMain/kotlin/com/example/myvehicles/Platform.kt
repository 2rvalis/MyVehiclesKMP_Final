package com.example.myvehicles

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform