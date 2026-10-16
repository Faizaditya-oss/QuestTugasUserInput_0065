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
            // Kartu biodata mengapung di atas Scaffold
            Surface(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(
                            R.dimen.form_overlap_offset
                        )
                    ),
                shape = RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.card_radius),
                    topEnd = dimensionResource(R.dimen.card_radius)
                ),
                color = colorResourceCompat(R.color.card_background),
                shadowElevation = dimensionResource(R.dimen.card_elevation)
            ) {

                Column(
                    modifier = Modifier
                        .heightIn(
                            max = dimensionResource(R.dimen.form_max_height)
                        )
                        .verticalScroll(rememberScrollState())
                        .padding(
                            horizontal = dimensionResource(
                                R.dimen.form_horizontal_padding
                            )
                        )
                        .padding(
                            top = dimensionResource(R.dimen.form_top_padding),
                            bottom = dimensionResource(R.dimen.form_bottom_padding)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.form_title),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = colorResourceCompat(R.color.color_primary)
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.space_small)
                        )
                    )

                    Text(
                        text = stringResource(R.string.form_subtitle),
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResourceCompat(R.color.text_secondary),
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.space_large)
                        )
                    )
