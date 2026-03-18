package com.example.praktam_2417051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PrakTAM_2417051021Theme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

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

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        items(GlowUpSource.glowUpList) { item ->
            GlowUpItem(glowUp = item)
        }

    }
}

@Composable
fun GlowUpItem(glowUp: GlowUp) {

    var isStarted by remember { mutableStateOf(false) }

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
                onClick = { isStarted = !isStarted },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isStarted)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(if (isStarted) "Done" else "Start")
            }

        }

    }
}