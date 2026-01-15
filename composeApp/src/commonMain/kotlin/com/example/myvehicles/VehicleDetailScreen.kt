package com.example.myvehicles

import Vehicle
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailScreen(
    vehicle: Vehicle,
    onDelete: () -> Unit,
    onUpdate: (Vehicle) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var isEditing by remember { mutableStateOf(false) }

    // States για όλα τα πεδία
    var brand by remember { mutableStateOf(vehicle.brand) }
    var model by remember { mutableStateOf(vehicle.model) }
    var color by remember { mutableStateOf(vehicle.color) }
    var kteoDate by remember { mutableStateOf(vehicle.kteoDate) }
    var tireSize by remember { mutableStateOf(vehicle.tireSize) }
    var pressureFrontPsi by remember { mutableStateOf(vehicle.pressureFrontPsi) }
    var pressureFrontBar by remember { mutableStateOf(vehicle.pressureFrontBar) }
    var pressureBackPsi by remember { mutableStateOf(vehicle.pressureBackPsi) }
    var pressureBackBar by remember { mutableStateOf(vehicle.pressureBackBar) }
    var serviceInfo by remember { mutableStateOf(vehicle.serviceInfo) }
    var imagePath by remember { mutableStateOf(vehicle.imagePath) }
    var licensePlatePath by remember { mutableStateOf(vehicle.licensePlatePath) }
    var registrationPath by remember { mutableStateOf(vehicle.registrationPath) }
    var insurancePath by remember { mutableStateOf(vehicle.insurancePath) }

    val mainImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { imagePath = it.toString() }
    }

    // ΒΕΛΤΙΩΜΕΝΗ ΣΥΝΑΡΤΗΣΗ ΑΝΟΙΓΜΑΤΟΣ PDF
    val openFile = { path: String?, forcePdf: Boolean ->
        if (!path.isNullOrEmpty()) {
            try {
                val uri = Uri.parse(path)
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    // Αν forcePdf είναι true (για την Ασφάλεια), επιβάλλουμε PDF mime type
                    val mimeType = if (forcePdf || path.lowercase().contains("pdf")) "application/pdf" else "image/*"
                    setDataAndType(uri, mimeType)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(Intent.createChooser(intent, "Επιλέξτε εφαρμογή PDF:"))
            } catch (e: Exception) {
                Toast.makeText(context, "Δεν βρέθηκε εφαρμογή για άνοιγμα PDF", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${vehicle.brand} ${vehicle.model}") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Πίσω") } },
                actions = {
                    if (isEditing) {
                        IconButton(onClick = {
                            onUpdate(vehicle.copy(
                                brand = brand, model = model, color = color, kteoDate = kteoDate,
                                tireSize = tireSize, pressureFrontPsi = pressureFrontPsi,
                                pressureFrontBar = pressureFrontBar, pressureBackPsi = pressureBackPsi,
                                pressureBackBar = pressureBackBar, serviceInfo = serviceInfo,
                                imagePath = imagePath, licensePlatePath = licensePlatePath,
                                registrationPath = registrationPath, insurancePath = insurancePath
                            ))
                            isEditing = false
                        }) { Icon(Icons.Default.Check, "Αποθήκευση", tint = Color(0xFF4CAF50)) }
                    } else {
                        IconButton(onClick = { isEditing = true }) { Icon(Icons.Default.Edit, "Επεξεργασία") }
                        IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, "Διαγραφή", tint = Color.Red) }
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().verticalScroll(rememberScrollState())) {

            // Κεντρική Φωτογραφία
            Box(
                modifier = Modifier.fillMaxWidth().height(220.dp).background(Color.LightGray)
                    .clickable(enabled = isEditing) { mainImageLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imagePath != null) {
                    AsyncImage(model = imagePath, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                    Icon(Icons.Default.DirectionsCar, null, modifier = Modifier.size(64.dp), tint = Color.White)
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                DetailTextField("Μάρκα", brand, isEditing) { brand = it }
                DetailTextField("Μοντέλο", model, isEditing) { model = it }
                DetailTextField("Χρώμα", color, isEditing) { color = it }
                DetailTextField("Λήξη ΚΤΕΟ", kteoDate, isEditing) { kteoDate = it }
                DetailTextField("Ελαστικά", tireSize, isEditing) { tireSize = it }

                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f)) { DetailTextField("F PSI", pressureFrontPsi, isEditing) { pressureFrontPsi = it } }
                    Box(modifier = Modifier.weight(1f)) { DetailTextField("F BAR", pressureFrontBar, isEditing) { pressureFrontBar = it } }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f)) { DetailTextField("R PSI", pressureBackPsi, isEditing) { pressureBackPsi = it } }
                    Box(modifier = Modifier.weight(1f)) { DetailTextField("R BAR", pressureBackBar, isEditing) { pressureBackBar = it } }
                }

                Spacer(Modifier.height(16.dp))
                DetailTextField("Service", serviceInfo, isEditing) { serviceInfo = it }

                Spacer(Modifier.height(24.dp))
                Text("Έγγραφα Οχήματος", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    // Πινακίδα & Άδεια (μπορεί να είναι εικόνες ή pdf)
                    DocumentBox("Πινακίδα", licensePlatePath, isEditing, { licensePlatePath = it }, { openFile(licensePlatePath, false) }, { licensePlatePath = null })
                    DocumentBox("Άδεια", registrationPath, isEditing, { registrationPath = it }, { openFile(registrationPath, false) }, { registrationPath = null })

                    // Ασφάλεια (Επιβολή PDF εικονιδίου και PDF reader)
                    DocumentBox("Ασφάλεια", insurancePath, isEditing, { insurancePath = it }, { openFile(insurancePath, true) }, { insurancePath = null }, isPdfOnly = true)
                }
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun DocumentBox(
    label: String,
    path: String?,
    isEditing: Boolean,
    onFileSelected: (String) -> Unit,
    onOpenFile: () -> Unit,
    onClear: () -> Unit,
    isPdfOnly: Boolean = false // Νέα παράμετρος
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { onFileSelected(it.toString()) }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.TopEnd) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        if (isEditing) launcher.launch("*/*")
                        else if (!path.isNullOrEmpty()) onOpenFile()
                    },
                contentAlignment = Alignment.Center
            ) {
                if (!path.isNullOrEmpty()) {
                    // Αν είναι PdfOnly ή το path περιέχει pdf, δείξε το εικονίδιο PDF
                    if (isPdfOnly || path.lowercase().contains("pdf")) {
                        Icon(Icons.Default.PictureAsPdf, "PDF", tint = Color.Red, modifier = Modifier.size(45.dp))
                    } else {
                        AsyncImage(model = path, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    }
                } else {
                    Icon(if (isEditing) Icons.Default.Add else Icons.Default.Description, null, tint = Color.LightGray)
                }
            }

            if (isEditing && !path.isNullOrEmpty()) {
                IconButton(
                    onClick = onClear,
                    modifier = Modifier.offset(x = 12.dp, y = (-12).dp).size(28.dp).background(Color.Red, CircleShape)
                ) {
                    Icon(Icons.Default.Close, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }
        Text(label, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun DetailTextField(label: String, value: String, isEditing: Boolean, onValueChange: (String) -> Unit) {
    if (isEditing) {
        OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
    } else {
        Column(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(if (value.isEmpty()) "-" else value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}