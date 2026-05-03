package com.example.praktam_2417051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.praktam_2417051021.model.GlowUp
import com.example.praktam_2417051021.model.GlowUpSource
import com.example.praktam_2417051021.ui.theme.PrakTAM_2417051021Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PrakTAM_2417051021Theme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    GlowUpScreen(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun GlowUpScreen(modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxSize()) {

        // 🔹 LazyRow
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(GlowUpSource.glowUpList) { item ->

                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(120.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.nama,
                            modifier = Modifier.size(60.dp)
                        )

                        Text(text = item.nama)

                    }

                }

            }
        }

        // 🔹 LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(GlowUpSource.glowUpList) { item ->
                GlowUpItem(glowUp = item)
            }
        }

    }
}

@Composable
fun GlowUpItem(glowUp: GlowUp) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = glowUp.imageRes),
                    contentDescription = glowUp.nama,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 8.dp)
                )

                Text(text = glowUp.nama)

                Text(
                    text = glowUp.deskripsi,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Button(
                    onClick = {
                        coroutineScope.launch {

                            isLoading = true

                            delay(2000)

                            snackbarHostState.showSnackbar(
                                "${glowUp.nama} berhasil diproses!"
                            )

                            isLoading = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Memproses...")
                    } else {
                        Text("Start")
                    }

                }

            }

        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}