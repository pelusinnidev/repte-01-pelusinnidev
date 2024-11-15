package com.lasalle.repte01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Repte()
        }
    }
}

@Composable
fun DropDownMenu(
    selectedIcon: ImageVector?,
    onIconSelected: (ImageVector) -> Unit, // Callback para actualizar el icono seleccionado
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Favorite,
        Icons.Default.Settings,
        Icons.Default.Share,
        Icons.Default.Info,
        Icons.Default.Person,
        Icons.Default.Phone,
        Icons.Default.Email,
        Icons.Default.Lock,
        Icons.Default.Check,
        Icons.Default.Close,
        Icons.Default.Delete,
        Icons.Default.Edit,
        Icons.Default.Menu,
        Icons.Default.Search,
        Icons.Default.Star,
    )

    Column(
        Modifier
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        OutlinedTextField(
            value = selectedIcon?.name ?: "Selecciona una icona",
            onValueChange = {},
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            icons.forEach { icon ->
                DropdownMenuItem(
                    text = { Text(icon.name) },
                    onClick = {
                        expanded = false
                        onIconSelected(icon) // Actualizamos el icono seleccionado
                    },
                    leadingIcon = { Icon(imageVector = icon, contentDescription = icon.name) }
                )
            }
        }
    }
}

@Composable
fun Repte() {
    var minValue by remember { mutableStateOf("") }
    var maxValue by remember { mutableStateOf("") }
    var sliderValue by remember { mutableStateOf(0f) }
    var selectedIcon by remember { mutableStateOf<ImageVector?>(null) }

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Repte 01",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )
        )

        // Sección del DropdownMenu
        DropDownMenu(
            selectedIcon = selectedIcon,
            onIconSelected = { selectedIcon = it } // Actualizamos el estado global
        )

        Textfields(
            minValue = minValue,
            maxValue = maxValue,
            onMinValueChange = { minValue = it },
            onMaxValueChange = { maxValue = it }
        )

        Sliders(
            minValue = minValue,
            maxValue = maxValue,
            sliderValue = sliderValue,
            onSliderValueChange = { sliderValue = it }
        )

        Button(
            onClick = { /* Lógica para enviar el formulario */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Enviar")
        }

        HorizontalDivider()

        Result(sliderValue = sliderValue, selectedIcon = selectedIcon)
    }
}

@Composable
fun Textfields(
    minValue: String,
    maxValue: String,
    onMinValueChange: (String) -> Unit,
    onMaxValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = minValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onMinValueChange(newValue)
                }
            },
            label = { Text("Valor mínim") },
            modifier = Modifier.width(150.dp)
        )

        OutlinedTextField(
            value = maxValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onMaxValueChange(newValue)
                }
            },
            label = { Text("Valor màxim") },
            modifier = Modifier.width(150.dp)
        )
    }
}

@Composable
fun Sliders(
    minValue: String,
    maxValue: String,
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit
) {
    val min = minValue.toIntOrNull() ?: 0
    val max = maxValue.toIntOrNull() ?: 100

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "Valor del Slider: ${sliderValue.toInt()}")
        Slider(
            value = sliderValue,
            onValueChange = { onSliderValueChange(it) },
            valueRange = min.toFloat()..max.toFloat(),
            steps = max - min - 1,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Result(
    sliderValue: Float,
    selectedIcon: ImageVector?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Asegura espacio alrededor
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally // Centra horizontalmente
    ) {
        BadgedBox(
            badge = {
                Badge {
                    Text(sliderValue.toInt().toString())
                }
            },
            content = {
                if (selectedIcon != null) {
                    Icon(
                        imageVector = selectedIcon,
                        contentDescription = selectedIcon.name,
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    Text("No icon selected")
                }
            }
        )
    }
}