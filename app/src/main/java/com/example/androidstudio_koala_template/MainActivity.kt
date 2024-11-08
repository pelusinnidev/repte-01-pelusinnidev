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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Repte()
                }
            }
        }
    }
}

@Composable
fun Repte() {
    Column(
        // Els
        Modifier
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
        Textfields()

        // Secció del Slider (Placeholder de moment)
        Sliders()

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