package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

/**
 * Η υλοποίηση του Constructor για το iOS.
 * Χρησιμοποιούμε το @Suppress("NO_ACTUAL_FOR_EXPECT") γιατί η σύνδεση
 * με την παραγόμενη κλάση της Room γίνεται κατά το compile time.
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase> {

    /**
     * Υλοποιούμε τη συνάρτηση initialize που απαιτείται.
     * Η instantiateImpl() είναι η εσωτερική συνάρτηση που δημιουργεί ο KSP
     * για να "γεννήσει" την πραγματική βάση δεδομένων.
     */
    actual override fun initialize(): VehicleDatabase = instantiateImpl()
}