package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences

@Composable
fun HydrationProgressScreen(
    navController: NavController,
    userPreferences: UserPreferences
) {
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        progress = userPreferences.getHydrationProgress()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Progress Hidrasi Mingguan", style = MaterialTheme.typography.headlineMedium)

        Text("Target: 7 Hari dalam seminggu")
        Text("Berhasil: $progress hari")

        LinearProgressIndicator(
            progress = progress / 7f,
            modifier = Modifier.fillMaxWidth().height(8.dp)
        )

        val sisa = 7 - progress
        if (sisa > 0) Text("Tinggal $sisa hari lagi untuk memenuhi target mingguan!")
        else Text("Selamat! Kamu memenuhi target hidrasi minggu ini!")

        Spacer(Modifier.weight(1f))
        OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Kembali")
        }
    }
}