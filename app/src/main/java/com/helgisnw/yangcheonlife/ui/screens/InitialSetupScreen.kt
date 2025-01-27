package com.helgisnw.yangcheonlife.ui.screens

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging
import com.helgisnw.yangcheonlife.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialSetupScreen() {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("app_settings", android.content.Context.MODE_PRIVATE) }

    var selectedGrade by remember { mutableStateOf(1) }
    var selectedClass by remember { mutableStateOf(1) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedSubjectB by remember { mutableStateOf("없음") }
    var selectedSubjectC by remember { mutableStateOf("없음") }
    var selectedSubjectD by remember { mutableStateOf("없음") }

    val subjects = listOf(
        "없음", "물리", "화학", "생명과학", "지구과학", "윤사", "정치와 법",
        "경제", "세계사", "한국지리", "탐구B", "탐구C", "탐구D"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.initial_setup),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.class_settings),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Grade Selection
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = String.format(stringResource(R.string.grade_format), selectedGrade),
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.grade)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        (1..3).forEach { grade ->
                            DropdownMenuItem(
                                text = { Text(String.format(stringResource(R.string.grade_format), grade)) },
                                onClick = { selectedGrade = grade }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Class Selection
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = String.format(stringResource(R.string.classroom_format), selectedClass),
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.classroom)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        (1..11).forEach { classNum ->
                            DropdownMenuItem(
                                text = { Text(String.format(stringResource(R.string.classroom_format), classNum)) },
                                onClick = { selectedClass = classNum }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.subject_selection),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Subject B Selection
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedSubjectB,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.subject_b)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        subjects.forEach { subject ->
                            DropdownMenuItem(
                                text = { Text(subject) },
                                onClick = { selectedSubjectB = subject }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Subject C Selection
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedSubjectC,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.subject_c)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        subjects.forEach { subject ->
                            DropdownMenuItem(
                                text = { Text(subject) },
                                onClick = { selectedSubjectC = subject }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Subject D Selection
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedSubjectD,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.subject_d)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        subjects.forEach { subject ->
                            DropdownMenuItem(
                                text = { Text(subject) },
                                onClick = { selectedSubjectD = subject }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications Section
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.alert_settings),
                    style = MaterialTheme.typography.titleMedium
                )
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                saveSettings(
                    prefs,
                    selectedGrade,
                    selectedClass,
                    notificationsEnabled,
                    selectedSubjectB,
                    selectedSubjectC,
                    selectedSubjectD
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(stringResource(R.string.done))
        }
    }
}

private fun saveSettings(
    prefs: SharedPreferences,
    grade: Int,
    classNum: Int,
    notificationsEnabled: Boolean,
    subjectB: String,
    subjectC: String,
    subjectD: String
) {
    prefs.edit().apply {
        putInt("defaultGrade", grade)
        putInt("defaultClass", classNum)
        putBoolean("notificationsEnabled", notificationsEnabled)
        putString("selectedSubjectB", subjectB)
        putString("selectedSubjectC", subjectC)
        putString("selectedSubjectD", subjectD)
        putBoolean("initialSetupCompleted", true)
        apply()
    }

    if (notificationsEnabled) {
        FirebaseMessaging.getInstance().subscribeToTopic("$grade-$classNum")
    }
}