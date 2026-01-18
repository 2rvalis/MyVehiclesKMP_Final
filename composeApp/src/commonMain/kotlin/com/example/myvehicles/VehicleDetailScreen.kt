package com.example.myvehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    var isEditing by remember { mutableStateOf(false) }
    val picker = getFilePicker()

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
                                imagePath = imagePath ?: "",
                                licensePlatePath = licensePlatePath ?: "",
                                registrationPath = registrationPath ?: "",
                                insurancePath = insurancePath ?: ""
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

            Box(
                modifier = Modifier.fillMaxWidth().height(220.dp).background(Color.LightGray)
                    .clickable(enabled = isEditing) { picker.pickImage { imagePath = it } },
                contentAlignment = Alignment.Center
            ) {
                if (!imagePath.isNullOrEmpty()) {
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
                    DocumentBox("Πινακίδα", licensePlatePath, isEditing, { licensePlatePath = it }, { openFilePlatform(licensePlatePath ?: "") }, { licensePlatePath = null })
                    DocumentBox("Άδεια", registrationPath, isEditing, { registrationPath = it }, { openFilePlatform(registrationPath ?: "") }, { registrationPath = null })
                    DocumentBox("Ασφάλεια", insurancePath, isEditing, { insurancePath = it }, { openFilePlatform(insurancePath ?: "") }, { insurancePath = null }, isPdfOnly = true)
                }
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun DocumentBox(label: String, path: String?, isEditing: Boolean, onFileSelected: (String?) -> Unit, onOpenFile: () -> Unit, onClear: () -> Unit, isPdfOnly: Boolean = false) {
    val picker = getFilePicker()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.TopEnd) {
            Box(
                modifier = Modifier.size(90.dp).clip(RoundedCornerShape(12.dp)).border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        if (isEditing) picker.pickFile { onFileSelected(it) }
                        else if (!path.isNullOrEmpty()) onOpenFile()
                    },
                contentAlignment = Alignment.Center
            ) {
                if (!path.isNullOrEmpty()) {
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
                IconButton(onClick = onClear, modifier = Modifier.offset(x = 12.dp, y = (-12).dp).size(28.dp).background(Color.Red, CircleShape)) {
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