package com.example.myvehicles

import com.example.myvehicles.Vehicle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleListScreen(
    vehicles: List<Vehicle>,
    onVehicleClick: (Long) -> Unit, // ΠΡΟΣΟΧΗ: Long, όχι Int
    onDeleteVehicle: (Vehicle) -> Unit,
    onAddVehicle: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Τα Οχήματά μου") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddVehicle) {
                Icon(Icons.Default.Add, contentDescription = "Προσθήκη")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            vehicles.forEach { vehicle ->
                VehicleItem(
                    vehicle = vehicle,
                    onClick = { onVehicleClick(vehicle.id) },
                    onDeleteClick = { onDeleteVehicle(vehicle) }
                )
            }
        }
    }
}

@Composable
fun VehicleItem(vehicle: Vehicle, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)).background(Color.LightGray)) {
                if (!vehicle.imagePath.isNullOrEmpty()) {
                    AsyncImage(model = vehicle.imagePath, contentDescription = null, contentScale = ContentScale.Crop)
                } else {
                    Icon(Icons.Default.DirectionsCar, null, modifier = Modifier.align(Alignment.Center))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(vehicle.brand, fontWeight = FontWeight.Bold)
                Text(vehicle.model, color = Color.Gray)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Διαγραφή", tint = Color.Red)
            }
        }
    }
}