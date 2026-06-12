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
fun SelfCareProgressScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val userPreferences = remember {
        UserPreferences(context)
    }

    var progress by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {

        progress =
            userPreferences.getSelfCareProgress()

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
            text = "Progress Self Care",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        LinearProgressIndicator(
            progress = {
                progress / 7f
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Target Mingguan: 7 Hari"
        )

        Text(
            text = "Progress: $progress / 7"
        )

    }

}