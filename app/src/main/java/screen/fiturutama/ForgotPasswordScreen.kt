package screen.fiturutama

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.praktam_2417051021.datastore.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit
) {

    val context = LocalContext.current
    val userPreferences = remember {
        UserPreferences(context)
    }

    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = emailInput,
            onValueChange = {
                emailInput = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
            },
            label = {
                Text("Password Baru")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            label = {
                Text("Konfirmasi Password Baru")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                scope.launch {

                    val savedUsername = userPreferences.getUsername()
                    val savedEmail = userPreferences.getEmail()

                    when {
                        username != savedUsername || emailInput != savedEmail -> {
                            message = "Username atau Email tidak cocok"
                        }

                        newPassword != confirmPassword -> {
                            message = "Konfirmasi password tidak cocok"
                        }

                        newPassword.isEmpty() -> {
                            message = "Password tidak boleh kosong"
                        }

                        else -> {
                            userPreferences.updatePassword(newPassword)
                            message = "Password berhasil diubah"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Password")
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = onBack
        ) {
            Text("Back to Login")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = message,
                color = if (message.contains("berhasil")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
