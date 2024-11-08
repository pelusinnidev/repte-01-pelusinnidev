package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Repte()
            }
        }
    }
}

@Composable
fun DropDownMenu(modifier: Modifier = Modifier) {
    // Estat per a la icona seleccionada i el desplegable
    var selectedIcon by remember { mutableStateOf<ImageVector?>(null) }
    var expanded by remember { mutableStateOf(false) }

    // Llista d'icones per defecte
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
                .clickable { expanded = true } // Mostrem el desplegable quan fem clic
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp)) // Afegim estil al Dropdown
        ) {
            icons.forEach { icon ->
                DropdownMenuItem(
                    text = { Text(icon.name) }, // Mostrem el nom de la icona com a text
                    onClick = {
                        expanded = false // Tanquem el desplegable
                        selectedIcon = icon // Assignem la icona seleccionada
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

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .padding(top = 50.dp)
    ) {
        // Secció del Títol
        Text(
            text = "Repte 01",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1) // Blau fosc
            )
        )

        // Secció del Dropdown amb una colecció d'icones
        DropDownMenu()

        // Textfields per a mínim i màxim del Slider
        Textfields(
            minValue = minValue,
            maxValue = maxValue,
            onMinValueChange = { minValue = it },
            onMaxValueChange = { maxValue = it }
        )

        // Secció del Slider
        Sliders(
            minValue = minValue,
            maxValue = maxValue,
            sliderValue = sliderValue,
            onSliderValueChange = { sliderValue = it }
        )

        // Botó per enviar el formulari
        Button(
            onClick = { /* Lògica per enviar el formulari */ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Enviar")
        }

        // Divider per separar seccions
        HorizontalDivider()

        // Mostra el resultat amb una composició dels valors seleccionats
        Result()
    }
}

@Composable
fun Textfields(
    minValue: String,
    maxValue: String,
    onMinValueChange: (String) -> Unit,
    onMaxValueChange: (String) -> Unit
) {
    // Organitzem els TextFields dins d'una fila amb espai entre ells
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Espai entre els Fields
    ) {
        // TextField per al valor mínim
        OutlinedTextField(
            value = minValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onMinValueChange(newValue)
                }
            },
            label = { Text("Valor mínim") },
            modifier = Modifier
                .width(150.dp) // Establim un tamany fixe
        )

        // TextField per al valor màxim
        OutlinedTextField(
            value = maxValue,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onMaxValueChange(newValue)
                }
            },
            label = { Text("Valor màxim") },
            modifier = Modifier
                .width(150.dp) // Establim un tamany fixe
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
    val min = minValue.toIntOrNull() ?: 0 // Valor mínim per al Slider
    val max = maxValue.toIntOrNull() ?: 100 // Valor màxim per al Slider

    // Slider que usa els valors dels TextFields
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "Valor del Slider: ${sliderValue.toInt()}")
        Slider(
            value = sliderValue,
            onValueChange = { onSliderValueChange(it) },
            valueRange = min.toFloat()..max.toFloat(),
            steps = max - min - 1, // Divisions del slider (tallets)
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Result() {
    // Placeholder per mostrar el resultat final
    Text(text = "Resultat")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReptePreview() {
    AndroidStudioKoalaTemplateTheme {
        Repte()
    }
}