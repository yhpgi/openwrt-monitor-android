package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R


@Composable
fun DialogSettingRadioButton(
    @StringRes title: Int,
    @StringRes description: Int,
    openDialog: Boolean,
    selectedTheme: String,
    onDismiss: () -> Unit,
    onSave: (selectedThemeString: String) -> Unit
) {
    val radioOptions = listOf(
        stringResource(R.string.setting_theme_follow_system),
        stringResource(R.string.setting_theme_light),
        stringResource(R.string.setting_theme_dark)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(selectedTheme) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = stringResource(id = title))
            },
            text = {
                Column(
                    modifier = Modifier.selectableGroup()
                ) {
                    Text(
                        text = stringResource(id = description),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    radioOptions.forEach { text ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                    }
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { onDismiss() }
                    ) {
                        Text("Dismiss")
                    }
                    Button(
                        onClick = {
                            onSave(selectedOption)
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        )
    }
}
