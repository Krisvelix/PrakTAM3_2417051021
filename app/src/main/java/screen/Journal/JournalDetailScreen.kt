package com.example.praktam_2417051021.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun JournalDetailScreen(
    navController: NavController,
    userPreferences: UserPreferences
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var journalText by remember { mutableStateOf("") }

    var progress by remember { mutableStateOf(0) }
    var lastDate by remember { mutableStateOf("") }
    var week by remember { mutableStateOf(0) }

    val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    // Load data awal
    LaunchedEffect(Unit) {
        progress = userPreferences.getJournalProgress()
        lastDate = userPreferences.getLastJournalDate()
        week = userPreferences.getJournalWeek()

        val currentWeek =
            Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)

        if (week != currentWeek) {
            progress = 0
            userPreferences.saveJournalProgress(0)
            userPreferences.saveJournalWeek(currentWeek)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Journaling Harian",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Progress: $progress / 7 hari"
        )

        OutlinedTextField(
            value = journalText,
            onValueChange = { journalText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Tulis jurnal kamu hari ini...") },
            minLines = 5
        )

        Button(
            onClick = {

                if (journalText.isBlank()) {
                    Toast.makeText(context, "Jurnal tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (lastDate == today) {
                    Toast.makeText(context, "Kamu sudah journaling hari ini", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                scope.launch {

                    val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                    val lastDate = userPreferences.getLastJournalDate()

                    if (lastDate == today) {
                        Toast.makeText(context, "Kamu sudah journaling hari ini", Toast.LENGTH_SHORT).show()
                        return@launch
                    }

                    val currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
                    val savedWeek = userPreferences.getJournalWeek()

                    var progress = userPreferences.getJournalProgress()

                    if (savedWeek != currentWeek) {
                        progress = 0
                        userPreferences.saveJournalWeek(currentWeek)
                    }

                    val newProgress = progress + 1
                    userPreferences.saveJournalProgress(newProgress)

                    userPreferences.saveLastJournalDate(today)

                    val oldHistory = userPreferences.getJournalHistory()

                    val newHistory = if (oldHistory.isEmpty()) {
                        "[$today]\n$journalText"
                    } else {
                        "$oldHistory\n\n[$today]\n$journalText"
                    }

                    userPreferences.saveJournalHistory(newHistory)

                    Toast.makeText(context, "Jurnal tersimpan", Toast.LENGTH_SHORT).show()

                    journalText = ""
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Jurnal")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("journal_progress")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lihat Progress")
        }

        Button(
            onClick = {
                navController.navigate("journal_history")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lihat History")
        }

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali")
        }
    }
}