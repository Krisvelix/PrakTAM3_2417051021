package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences

@Composable
fun JournalProgressScreen(
    navController: NavController,
    userPreferences: UserPreferences
) {

    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        progress = userPreferences.getJournalProgress()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Progress Journaling",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Target: 7 hari"
        )

        Text(
            text = "Sudah selesai: $progress hari"
        )

        LinearProgressIndicator(
            progress = progress / 7f,
            modifier = Modifier.fillMaxWidth()
        )

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