package com.example.myvehicles // Πρόσθεσε το package

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles") // Πρόσθεσε το tableName εδώ
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val brand: String,
    val model: String,
    val color: String = "",
    val kteoDate: String = "",
    val tireSize: String = "",
    val pressureFrontPsi: String = "",
    val pressureFrontBar: String = "",
    val pressureBackPsi: String = "",
    val pressureBackBar: String = "",
    val serviceInfo: String = "",
    val imagePath: String? = null,
    val licensePlatePath: String? = null,
    val registrationPath: String? = null,
    val insurancePath: String? = null
)