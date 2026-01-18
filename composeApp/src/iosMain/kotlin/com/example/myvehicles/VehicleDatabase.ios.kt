// iosMain
package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase> {
    // Αντί για instantiateImpl(), αν το build αποτυγχάνει,
    // η Room αναζητά την κλάση που παράγεται αυτόματα.
    // Αλλά για αρχή, βεβαιώσου ότι ΔΕΝ υπάρχει η λέξη 'actual' πριν το override αν δεν υπάρχει στο expect.
}