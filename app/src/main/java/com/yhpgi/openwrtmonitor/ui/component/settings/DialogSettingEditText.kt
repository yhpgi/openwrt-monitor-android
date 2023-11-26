package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSettingEditText(
    @StringRes title: Int,
    @StringRes description: Int,
    placeholder: String,
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (newValue: String) -> Unit
) {
    var text by remember { mutableStateOf(placeholder) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = stringResource(id = title))
            },
            text = {
                Column {
                    Text(text = stringResource(id = description))
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = {
                            Text(text = placeholder)
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { onDismiss() }
                    ) {
                        Text("Dismiss")
                    }
                    Button(
                        onClick = {
                            onSave(text)
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        )
    }
}

//@Preview
//@Composable
//fun SettingsDialogPreview() {
//    OpenWrtMonitorTheme {
//        SettingsDialog(
//            title = "Preview Judul",
//            description = "Preview Deskripsi",
//            placeholder = "Preview Placeholder",
//            openDialog = true,
//            onDismiss = {},
//
//        )
//    }
//}