package com.example.praktam_2417051021.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SkincareTypeScreen(
    navController: NavController
) {

    val skinTypes = listOf(
        "Oily (Berminyak)",
        "Dry (Kering)",
        "Combination",
        "Acne (Jerawat)",
        "Sensitive"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Pilih Jenis Kulit",
            style = MaterialTheme.typography.headlineMedium
        )

        skinTypes.forEach { type ->

            Button(
                onClick = {
                    navController.navigate("skincare_detail/$type")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(type)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

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