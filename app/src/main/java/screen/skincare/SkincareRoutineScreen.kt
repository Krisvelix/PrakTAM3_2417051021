package com.example.praktam_2417051021.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SkincareRoutineScreen(
    navController: NavController,
    skinType: String,
    userPreferences: UserPreferences
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val days = listOf("Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu")

    var selectedDay by remember { mutableStateOf(days[0]) }
    var morningDone by remember { mutableStateOf(false) }
    var nightDone by remember { mutableStateOf(false) }

    val today = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Skincare Routine", style = MaterialTheme.typography.headlineMedium)
        Text("Skin Type: $skinType")
        Text("Hari ini: $today")

        Spacer(Modifier.height(8.dp))
        Text("Pilih Hari")

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(days) { day ->
                val selected = selectedDay == day

                Card(
                    onClick = { selectedDay = day },
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        day,
                        modifier = Modifier.padding(12.dp),
                        color =
                            if (selected) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Text("🌞 Morning Routine")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = morningDone, onCheckedChange = { morningDone = it })
            Text(
                when (skinType) {
                    "Oily (Berminyak)" -> "Cleanser - Toner - Sunscreen"
                    "Dry (Kering)" -> "Cleanser - Toner - Moisturizer"
                    "Sensitive (Sensitif)" -> "Gentle Cleanser - Sunscreen"
                    "Acne (Jerawat)" -> "Salicylic Cleanser - Treatment"
                    else -> "Basic Routine"
                }
            )
        }
        Text("🌙 Night Routine")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = nightDone, onCheckedChange = { nightDone = it })
            Text(
                when (skinType) {
                    "Oily (Berminyak)" -> "Cleanser - Toner - Moisturizer"
                    "Dry (Kering)" -> "Cleanser - Serum - Moisturizer"
                    "Sensitive (Sensitif)" -> "Gentle Cleanser"
                    "Acne (Jerawat)" -> "Treatment - Moisturizer"
                    else -> "Basic Night Routine"
                }
            )
        }

        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                scope.launch {
                    val lastDate = userPreferences.getLastSkincareDate()


                    if (lastDate == today) {
                        Toast.makeText(context, "Anda sudah melakukan skincare hari ini!", Toast.LENGTH_SHORT).show()
                        return@launch
                    }

                    val currentProgress = userPreferences.getSkincareProgress()
                    val newProgress = currentProgress + 1
                    
                    // Simpan progress, jika sudah 7 reset ke 0
                    if (newProgress >= 7) {
                        userPreferences.saveSkincareProgress(0)
                        Toast.makeText(context, "Selamat! Target 7 hari tercapai. Progress direset.", Toast.LENGTH_LONG).show()
                    } else {
                        userPreferences.saveSkincareProgress(newProgress)
                    }

                    // Simpan tanggal terakhir input
                    userPreferences.saveLastSkincareDate(today)

                    // Simpan ke Riwayat (Permanen)
                    val oldHistory = userPreferences.getSkincareHistory()
                    val newEntry = "[$today] $skinType ($selectedDay)\n" +
                                   "Status: ${if (morningDone) "Pagi ✔" else "Pagi ❌"} | ${if (nightDone) "Malam ✔" else "Malam ❌"}"
                    
                    val updatedHistory = if (oldHistory.isEmpty()) newEntry else "$oldHistory\n\n$newEntry"
                    userPreferences.saveSkincareHistory(updatedHistory)

                    Toast.makeText(context, "Skincare berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    navController.navigate("skincare_history")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Skincare")
        }
        Button(
            onClick = { navController.navigate("skincare_progress") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Lihat Progress")
        }
        Button(
            onClick = { navController.navigate("skincare_history") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Text("Lihat Riwayat")
        }
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali")
        }
    }
}