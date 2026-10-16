package com.faiz.questtugasuserinput_0065

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                BiodataScreen()
            }
        }
    }
}

@Composable
fun BiodataScreen() {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var experienceExpanded by remember { mutableStateOf(false) }

    var selectedSkills by remember {
        mutableStateOf(setOf<String>())
    }

    val experienceOptions =
        stringArrayResource(R.array.experience_options)

    val skills = listOf(
        stringResource(R.string.skill_android),
        stringResource(R.string.skill_uiux),
        stringResource(R.string.skill_web)
    )

    val primaryColor = colorResourceCompat(R.color.color_primary)

    val emailValid = email.isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val canProceed =
        firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                emailValid &&
                birthDate.isNotBlank() &&
                experience.isNotBlank() &&
                selectedSkills.isNotEmpty()

    val dateFormat = stringResource(R.string.date_format)

    val successMessage = stringResource(
        R.string.form_success,
        firstName,
        lastName
    )

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResourceCompat(R.color.color_primary),
        unfocusedBorderColor = colorResourceCompat(R.color.field_border),
        focusedTextColor = colorResourceCompat(R.color.text_primary),
        unfocusedTextColor = colorResourceCompat(R.color.text_primary),
        focusedPlaceholderColor = colorResourceCompat(R.color.text_primary),
        unfocusedPlaceholderColor = colorResourceCompat(R.color.text_primary),
        cursorColor = colorResourceCompat(R.color.color_primary)
    )

    Scaffold(
        containerColor = colorResourceCompat(R.color.screen_background)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Background bagian atas
            Image(
                painter = painterResource(R.drawable.bg_biodata_neon),
                contentDescription = stringResource(
                    R.string.background_description
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(R.dimen.background_height)
                    )
                    .align(Alignment.TopCenter)
            )