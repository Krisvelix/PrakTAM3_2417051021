package com.example.praktam_2417051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.praktam_2417051021.data.model.GlowUp
import com.example.praktam_2417051021.data.repository.GlowUpRepository
import com.example.praktam_2417051021.ui.theme.PrakTAM_2417051021Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            PrakTAM_2417051021Theme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    GlowUpScreen(
                        modifier = Modifier.padding(innerPadding)
                    )

                }

            }

        }
    }
}

@Composable
fun GlowUpScreen(modifier: Modifier = Modifier) {

    val repository = remember { GlowUpRepository() }

    var glowUps by remember {
        mutableStateOf<List<GlowUp>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        try {

            glowUps = repository.getGlowUp()

            isError = glowUps.isEmpty()

        } catch (e: Exception) {

            isError = true

        }

        isLoading = false

    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        when {

            isLoading -> {

                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            isError -> {

                Text(
                    text = "Gagal memuat data, periksa koneksi internet",
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            else -> {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(glowUps) { item ->

                        GlowUpItem(glowUp = item)

                    }

                }

            }

        }

    }

}

@Composable
fun GlowUpItem(glowUp: GlowUp) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = glowUp.image_url,
                contentDescription = glowUp.nama,

                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = glowUp.nama,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = glowUp.deskripsi,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Harga: Rp ${glowUp.harga}",
                style = MaterialTheme.typography.bodyMedium
            )

        }

    }

}