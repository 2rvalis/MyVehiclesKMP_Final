package com.example.myvehicles

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun getFilePicker(): FilePicker {
    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Το callback θα υλοποιηθεί μέσω της διεπαφής
    }

    // Επιστρέφουμε ένα αντικείμενο που γεφυρώνει το Compose Launcher με το interface μας
    return object : FilePicker {
        override fun pickImage(onResult: (String?) -> Unit) {
            // Σημείωση: Λόγω περιορισμών του Compose, στην πράξη καλούμε
            // απευθείας τους launchers μέσα από το UI για μέγιστη συμβατότητα
        }
        override fun pickFile(onResult: (String?) -> Unit) {
            // Αντίστοιχα εδώ
        }
    }
}

actual fun openFilePlatform(path: String?) {
    if (path.isNullOrEmpty()) return
    // Εδώ προσθέτεις το Intent logic που είχες για να ανοίγει το PDF
}