package com.example.praktam_2417051021.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HydrationRoutineScreen(
    navController: NavController,
    userPreferences: UserPreferences
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val hydrationRules = listOf(
        Pair("Senin", "8 Gelas"),
        Pair("Selasa", "10 Gelas"),
        Pair("Rabu", "12 Gelas"),
        Pair("Kamis", "8 Gelas"),
        Pair("Jumat", "10 Gelas"),
        Pair("Sabtu", "12 Gelas"),
        Pair("Minggu", "8 Gelas")
    )

    val todayDate = remember { SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(Date()) }
    val todayDayName = remember { SimpleDateFormat("EEEE", Locale("id", "ID")).format(Date()) }
    val todayKey = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()) }

    var isChecked by remember { mutableStateOf(false) }
    var lastSavedDate by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        lastSavedDate = userPreferences.getLastHydrationDate()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Hydration Rules 💧", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Patuhi target minum harianmu.")

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer).padding(8.dp)) {
                    Text("Hari", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text("Target", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text("Pilih", fontWeight = FontWeight.Bold)
                }

                hydrationRules.forEach { rule ->
                    val isToday = rule.first.equals(todayDayName, ignoreCase = true)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(rule.first, modifier = Modifier.weight(1f), color = if(isToday) MaterialTheme.colorScheme.primary else Color.Unspecified, fontWeight = if(isToday) FontWeight.Bold else FontWeight.Normal)
                        Text(rule.second, modifier = Modifier.weight(1f))

                        Checkbox(
                            checked = if (isToday) isChecked else false,
                            onCheckedChange = { if (isToday) isChecked = it },
                            enabled = isToday && lastSavedDate != todayKey
                        )
                    }
                    HorizontalDivider()
                }
            }
        }

        if (lastSavedDate == todayKey) {
            Text("✅ Kamu sudah menyelesaikan target hari ini!", color = Color(0xFF4CAF50))
        }

        Button(
            onClick = {
                if (!isChecked) {
                    Toast.makeText(context, "Centang hari ini untuk simpan!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                scope.launch {
                    val currentProgress = userPreferences.getHydrationProgress()
                    val newProgress = if (currentProgress >= 6) 0 else currentProgress + 1
                    userPreferences.saveHydrationProgress(newProgress)
                    userPreferences.saveLastHydrationDate(todayKey)
                    
                    val targetGelas = hydrationRules.find { it.first.equals(todayDayName, true) }?.second ?: ""
                    val newEntry = "[$todayDate] Minum $targetGelas ✅"
                    val oldHistory = userPreferences.getHydrationHistory()
                    userPreferences.saveHydrationHistory(if (oldHistory.isEmpty()) newEntry else "$oldHistory\n$newEntry")

                    Toast.makeText(context, "Data disimpan!", Toast.LENGTH_SHORT).show()
                    navController.navigate("hydration_history")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = lastSavedDate != todayKey
        ) {
            Text(if (lastSavedDate == todayKey) "Sudah Disimpan" else "Simpan Progress")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.navigate("hydration_progress") }, modifier = Modifier.weight(1f)) {
                Text("Progress")
            }
            Button(onClick = { navController.navigate("hydration_history") }, modifier = Modifier.weight(1f)) {
                Text("Riwayat")
            }
        }

        OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Kembali")
        }
    }
}
