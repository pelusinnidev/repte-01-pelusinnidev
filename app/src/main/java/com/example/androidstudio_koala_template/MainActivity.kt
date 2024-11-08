package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Repte()
                }
            }
        }
    }
}

@Composable
fun Repte() {
    Column {
        // Secció del Títol
        Text(text = "Repte 01")

        // Secció del Dropdown amb una colecció de hobbies (d'exemple)
        DropDownMenu()

        // Textfields per a mínim i màxim del Slider
        Textfields()

        // Secció del Slider (Placeholder de moment)
        Sliders()

        // Botó per enviar el formulàri
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
fun DropDownMenu(modifier: Modifier = Modifier) {
    // Estat per al text seleccionat i el desplegable
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val hobbies = listOf("Play music", "Practice sport", "Programming", "Reading", "Other")

    Column(
        Modifier
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
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
            hobbies.forEach { hobby ->
                DropdownMenuItem(
                    text = { Text(hobby) }, // Mostrem el hobby com a text
                    onClick = {
                        expanded = false // Tanquem el desplegable
                        selectedText = hobby // Assignem el hobby seleccionat
                    }
                )
            }
        }
    }
}

@Composable
fun Textfields() {
    // Placeholder per als Textfields (p. ex., valors de mínim i màxim)
    Text(text = "Textfields")
}

@Composable
fun Sliders() {
    // Placeholder per al Slider
    Text(text = "Slider")
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