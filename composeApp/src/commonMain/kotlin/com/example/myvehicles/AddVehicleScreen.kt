package com.example.myvehicles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehicleScreen(onSave: (Vehicle) -> Unit, onCancel: () -> Unit) {
    var brand by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var kteoDate by remember { mutableStateOf("") }
    var tireSize by remember { mutableStateOf("") }
    var pressureFrontPsi by remember { mutableStateOf("") }
    var pressureFrontBar by remember { mutableStateOf("") }
    var pressureBackPsi by remember { mutableStateOf("") }
    var pressureBackBar by remember { mutableStateOf("") }
    var serviceInfo by remember { mutableStateOf("") }

    var imagePath by remember { mutableStateOf<String?>(null) }
    var licensePlatePath by remember { mutableStateOf<String?>(null) }
    var registrationPath by remember { mutableStateOf<String?>(null) }
    var insurancePath by remember { mutableStateOf<String?>(null) }

    val picker = getFilePicker()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Νέα Καταχώρηση") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Πίσω")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (brand.isNotBlank() && model.isNotBlank()) {
                            onSave(Vehicle(
                                brand = brand, model = model, color = color,
                                kteoDate = kteoDate, tireSize = tireSize,
                                pressureFrontPsi = pressureFrontPsi, pressureFrontBar = pressureFrontBar,
                                pressureBackPsi = pressureBackPsi, pressureBackBar = pressureBackBar,
                                serviceInfo = serviceInfo,
                                imagePath = imagePath ?: "",
                                licensePlatePath = licensePlatePath ?: "",
                                registrationPath = registrationPath ?: "",
                                insurancePath = insurancePath ?: ""
                            ))
                        }
                    }) {
                        Icon(Icons.Default.Save, contentDescription = "Αποθήκευση")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).verticalScroll(rememberScrollState())) {

            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant).clickable {
                        picker.pickImage { path -> imagePath = path }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imagePath != null) {
                    AsyncImage(model = imagePath, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.AddAPhoto, null, modifier = Modifier.size(48.dp))
                        Text("Φωτογραφία Οχήματος")
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(value = brand, onValueChange = { brand = it }, label = { Text("Μάρκα *") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = model, onValueChange = { model = it }, label = { Text("Μοντέλο *") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = color, onValueChange = { color = it }, label = { Text("Χρώμα") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = kteoDate, onValueChange = { kteoDate = it }, label = { Text("Λήξη ΚΤΕΟ") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = tireSize, onValueChange = { tireSize = it }, label = { Text("Ελαστικά") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = pressureFrontPsi, onValueChange = { pressureFrontPsi = it }, label = { Text("F PSI") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = pressureFrontBar, onValueChange = { pressureFrontBar = it }, label = { Text("F BAR") }, modifier = Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = pressureBackPsi, onValueChange = { pressureBackPsi = it }, label = { Text("R PSI") }, modifier = Modifier.weight(1f))
                OutlinedTextField(value = pressureBackBar, onValueChange = { pressureBackBar = it }, label = { Text("R BAR") }, modifier = Modifier.weight(1f))
            }

            OutlinedTextField(value = serviceInfo, onValueChange = { serviceInfo = it }, label = { Text("Service") }, modifier = Modifier.fillMaxWidth().height(100.dp))

            Spacer(Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                AddDocBox("Πινακίδα", licensePlatePath) { licensePlatePath = it }
                AddDocBox("Άδεια", registrationPath) { registrationPath = it }
                AddDocBox("Ασφάλεια", insurancePath) { insurancePath = it }
            }
        }
    }
}

@Composable
fun AddDocBox(label: String, path: String?, onSelected: (String?) -> Unit) {
    val picker = getFilePicker()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray).clickable {
            picker.pickFile { onSelected(it) }
        }, contentAlignment = Alignment.Center) {
            if (path != null) {
                if (path.contains("pdf")) Icon(Icons.Default.PictureAsPdf, null, tint = Color.Red)
                else AsyncImage(model = path, contentDescription = null, contentScale = ContentScale.Crop)
            } else Icon(Icons.Default.Add, null)
        }
        Text(label, fontSize = 12.sp)
    }
}