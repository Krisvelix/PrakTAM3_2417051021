package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences

@Composable
fun JournalHistoryScreen(
    navController: NavController,
    userPreferences: UserPreferences
) {

    var history by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        history = userPreferences.getJournalHistory()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "History Journaling",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = if (history.isEmpty()) "Belum ada jurnal" else history
            )
        }

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali")
        }
    }
}