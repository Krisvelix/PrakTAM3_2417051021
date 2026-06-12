package com.example.praktam_2417051021

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051021.datastore.UserPreferences
import com.example.praktam_2417051021.screen.*
import screen.fiturutama.RegisterScreen
import screen.fiturutama.LoginScreen
import screen.fiturutama.ForgotPasswordScreen
import screen.fiturutama.EditProfileScreen
import screen.fiturutama.ChangePasswordScreen
import screen.profile.ProfileScreen
import com.example.praktam_2417051021.ui.theme.PrakTAM_2417051021Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PrakTAM_2417051021Theme {
                GlowUpApp()
            }
        }
    }
}

@Composable
fun GlowUpApp() {

    val navController = rememberNavController()

    // IMPORTANT: satu instance UserPreferences
    val userPreferences = UserPreferences(
        context = androidx.compose.ui.platform.LocalContext.current
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("splash") {
                SplashScreen(
                    onNavigateToLogin = {
                        navController.navigate("login") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

            composable("login") {
                LoginScreen(
                    onLoginSuccess = { navController.navigate("home") },
                    onRegisterClick = { navController.navigate("register") },
                    onForgotClick = { navController.navigate("forgot") }
                )
            }

            composable("register") {
                RegisterScreen(
                    onRegisterSuccess = { navController.popBackStack() },
                    onBackToLogin = { navController.popBackStack() }
                )
            }

            composable("forgot") {
                ForgotPasswordScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("home") {
                HomeScreen(navController)
            }

            composable("profile") {
                ProfileScreen(navController, userPreferences)
            }

            composable("edit_profile") {
                EditProfileScreen(navController, userPreferences)
            }

            composable("change_password") {
                ChangePasswordScreen(navController, userPreferences)
            }

            // ================= WORKOUT =================
            composable("workout_detail") {
                WorkoutDetailScreen(navController)
            }

            composable("progress") {
                ProgressScreen(navController)
            }

            composable("history") {
                HistoryScreen(navController)
            }

            // ================= SELF CARE =================
            composable("selfcare_detail") {
                SelfCareDetailScreen(navController)
            }

            composable("selfcare_progress") {
                SelfCareProgressScreen(navController)
            }

            composable("selfcare_history") {
                SelfCareHistoryScreen(navController)
            }

            // ================= JOURNAL =================
            composable("journal_detail") {
                JournalDetailScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            composable("journal_progress") {
                JournalProgressScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            composable("journal_history") {
                JournalHistoryScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            // ================= SKINCARE =================
            composable("skincare_detail") {
                SkincareRoutineScreen(
                    navController = navController,
                    skinType = "Oily (Berminyak)",
                    userPreferences = userPreferences
                )
            }

            composable("skincare_progress") {
                SkincareProgressScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            composable("skincare_history") {
                SkincareHistoryScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }
            composable("skincare_type") {
                SkincareTypeScreen(navController)
            }

            composable("skincare_detail/{skinType}") { backStack ->
                val skinType = backStack.arguments?.getString("skinType") ?: ""

                SkincareRoutineScreen(
                    navController = navController,
                    skinType = skinType,
                    userPreferences = userPreferences
                )
            }

            // ================= HYDRATION =================
            composable("hydration_routine") {
                HydrationRoutineScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            composable("hydration_history") {
                HydrationHistoryScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }

            composable("hydration_progress") {
                HydrationProgressScreen(
                    navController = navController,
                    userPreferences = userPreferences
                )
            }
        }
    }
}
