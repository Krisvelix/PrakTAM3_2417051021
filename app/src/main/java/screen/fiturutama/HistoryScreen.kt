package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051021.datastore.UserPreferences

@Composable
fun HistoryScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val userPreferences = remember {
        UserPreferences(context)
    }

    var history by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        history =
            userPreferences.getWorkoutHistory()

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
            text = "Riwayat Workout",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                if (history.isEmpty()) {

                    Text(
                        text = "Belum ada riwayat workout"
                    )

                } else {

                    Text(
                        text = history
                    )

                }

            }

        }

    }

}