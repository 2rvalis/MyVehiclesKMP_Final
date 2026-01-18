package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

// Εδώ ΔΕΝ βάζουμε το @ConstructedBy.
// Επίσης, ΔΕΝ χρειάζεται να κάνεις override την initialize,
// η Room την υλοποιεί αυτόματα στο παρασκήνιο.
@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>