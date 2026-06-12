package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import java.util.Calendar

@Composable
fun SelfCareDetailScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val userPreferences = remember {
        UserPreferences(context)
    }

    val coroutineScope = rememberCoroutineScope()

    var progress by remember {
        mutableStateOf(0)
    }

    var lastDate by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    var completedToday by remember {
        mutableStateOf(false)
    }

    val calendar = Calendar.getInstance()

    val todayDate =
        SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        ).format(Date())

    val currentWeek =
        calendar.get(Calendar.WEEK_OF_YEAR)

    var lastSelfCareDate by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        val currentWeek =
            Calendar.getInstance()
                .get(Calendar.WEEK_OF_YEAR)

        val savedWeek =
            userPreferences.getSelfCareWeek()

        if (savedWeek != currentWeek) {

            userPreferences.saveSelfCareProgress(0)

            userPreferences.saveSelfCareWeek(
                currentWeek
            )

        }

        progress =
            userPreferences.getSelfCareProgress()

        lastSelfCareDate =
            userPreferences.getLastSelfCareDate()

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
            text = "🧘 Self Care Time",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Target:")

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text("Luangkan waktu 15 menit untuk diri sendiri.")

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text("Contoh aktivitas:")

                Text("• Mendengarkan musik")
                Text("• Membaca buku")
                Text("• Journaling")
                Text("• Meditasi")
                Text("• Menonton film")
                Text("• Istirahat tanpa gadget")

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

                Text("Progress Mingguan")

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                LinearProgressIndicator(
                    progress = {
                        progress / 7f
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text("$progress / 7 Hari")

            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),

            onClick = {

                coroutineScope.launch {

                    val savedDate =
                        userPreferences.getLastSelfCareDate()

                    if (savedDate == todayDate) {

                        message =
                            "Self Care hari ini sudah dilakukan."

                        return@launch

                    }

                    var currentProgress =
                        userPreferences.getSelfCareProgress()

                    if (currentProgress < 7) {

                        currentProgress++

                    }

                    progress = currentProgress

                    userPreferences.saveSelfCareProgress(
                        currentProgress
                    )

                    userPreferences.saveLastSelfCareDate(
                        todayDate
                    )

                    lastSelfCareDate =
                        todayDate

                    val currentWeek =
                        Calendar.getInstance()
                            .get(Calendar.WEEK_OF_YEAR)

                    userPreferences.saveSelfCareWeek(
                        currentWeek
                    )

                    val oldHistory =
                        userPreferences.getSelfCareHistory()

                    val today =
                        SimpleDateFormat(
                            "dd MMM yyyy",
                            Locale.getDefault()
                        ).format(Date())

                    val newHistory =
                        "$today - Self Care 15 Menit Selesai\n$oldHistory"

                    userPreferences.saveSelfCareHistory(
                        newHistory
                    )

                    completedToday = true

                    message =
                        "🎉 Self Care hari ini berhasil diselesaikan!"

                }

            }

        ) {

            Text(
                    if (lastSelfCareDate == todayDate)

                        "Self Care Hari Ini Selesai ✔"

                    else

                        "Selesaikan Self Care Hari Ini"

                )

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        if (message.isNotEmpty()) {

            Text(message)

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    "selfcare_progress"
                )
            }
        ) {

            Text("Lihat Progress")

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(
                    "selfcare_history"
                )
            }
        ) {

            Text("Lihat History")

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
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        "🎉 Self Care Hari Ini Berhasil!"
                    )

                    Text(
                        "Durasi: 15 Menit"
                    )

                }

            }

        }

    }

}