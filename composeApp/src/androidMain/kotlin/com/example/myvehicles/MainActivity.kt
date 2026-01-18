package com.example.myvehicles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ΑΥΤΟ ΕΙΝΑΙ ΤΟ ΠΙΟ ΣΗΜΑΝΤΙΚΟ:
        // Αρχικοποιούμε το globalContext ώστε το androidMain/DatabaseBuilder
        // να ξέρει πού να δημιουργήσει το αρχείο .db
        globalContext = applicationContext

        setContent {
            // Χρησιμοποιούμε τη συνάρτηση getDatabase() που ορίσαμε στο commonMain.
            // Αυτή η συνάρτηση καλεί εσωτερικά τον getDatabaseBuilder()
            // ο οποίος είναι διαφορετικός για Android και iOS (iPhone).
            val database: VehicleDatabase = remember { getDatabase() }

            // Το Repository παίρνει το DAO από την κοινή βάση
            val repository: VehicleRepository = remember { VehicleRepository(database.vehicleDao()) }

            val viewModelFactory = VehicleViewModel.factory(repository)

            // Εκκίνηση της κοινής App
            App(viewModelFactory = viewModelFactory)
        }
    }
}