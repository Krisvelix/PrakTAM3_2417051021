package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar
import androidx.compose.ui.platform.LocalContext
import com.example.praktam_2417051021.datastore.UserPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WorkoutDetailScreen(
    navController: NavController
) {
    var lastWorkoutDate by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val userPreferences = remember {
        UserPreferences(context)
    }

    val coroutineScope = rememberCoroutineScope()

    var completedToday by remember {
        mutableStateOf(false)
    }

    var progress by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {

        progress =
            userPreferences.getWorkoutProgress()

        lastWorkoutDate =
            userPreferences.getLastWorkoutDate()

    }

    val dayOfWeek =
        Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    val todayDate =
        SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        ).format(Date())

    val isWorkoutDay =
        dayOfWeek == Calendar.MONDAY ||
                dayOfWeek == Calendar.WEDNESDAY ||
                dayOfWeek == Calendar.FRIDAY

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Kembali")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Workout 30 Menit",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Jadwal Workout"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text("✓ Senin")
                Text("✓ Rabu")
                Text("✓ Jumat")

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Durasi: 30 Menit"
                )

                Text(
                    text = "Target Mingguan: 3x Workout"
                )

            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Progress Mingguan"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                LinearProgressIndicator(
                    progress = { progress / 3f },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "$progress / 3 Workout Selesai"
                )

            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {

                if (!isWorkoutDay) {

                    message =
                        "Hari ini bukan jadwal workout"

                    return@Button

                }


                if (lastWorkoutDate == todayDate) {

                    message =
                        "Workout hari ini sudah selesai"

                    return@Button

                }

                completedToday = true

                if (progress < 3) {
                    progress++
                }

                coroutineScope.launch {

                    userPreferences.saveWorkoutProgress(
                        progress
                    )

                    userPreferences.saveLastWorkoutDate(
                        todayDate
                    )

                    lastWorkoutDate =
                        todayDate

                    val currentHistory =
                        userPreferences.getWorkoutHistory()

                    val today =
                        SimpleDateFormat(
                            "dd MMM yyyy",
                            Locale.getDefault()
                        ).format(Date())

                    val newHistory =
                        "$today - Workout 30 Menit Selesai\n$currentHistory"

                    userPreferences.saveWorkoutHistory(
                        newHistory
                    )

                }

                message =
                    "Workout berhasil diselesaikan 🎉"

            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (lastWorkoutDate == todayDate)
                        "Workout Hari Ini Selesai ✔"
                    else
                        "Selesaikan Workout Hari Ini"
            )

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        if (message.isNotEmpty()) {

            Text(
                text = message
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

        }

        Button(
            onClick = {
                navController.navigate("progress")
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Lihat Progress"
            )

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                navController.navigate("history")
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Lihat History"
            )

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (completedToday) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🎉 Workout Hari Ini Berhasil!"
                    )

                    Text(
                        text = "Durasi: 30 Menit"
                    )

                }

            }

        }

    }

}