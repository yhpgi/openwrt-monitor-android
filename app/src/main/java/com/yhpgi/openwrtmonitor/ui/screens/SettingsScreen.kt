package com.yhpgi.openwrtmonitor.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.activity.MainActivity
import com.yhpgi.openwrtmonitor.ui.component.settings.DialogSettingEditText
import com.yhpgi.openwrtmonitor.ui.component.settings.DialogSettingRadioButton
import com.yhpgi.openwrtmonitor.ui.component.settings.SettingItemsApplication
import com.yhpgi.openwrtmonitor.ui.component.settings.SettingItemsGeneral
import com.yhpgi.openwrtmonitor.ui.component.settings.SettingItemsOther
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    mainViewModel: MainViewModel,
    mainActivity: MainActivity
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.navbar_settings)
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            ElevatedCard(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.settings_title_application),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                SettingItemsApplication(
                    mainViewModel = mainViewModel
                )
            }
            ElevatedCard(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.settings_title_general),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                SettingItemsGeneral(
                    mainViewModel = mainViewModel,
                    mainActivity = mainActivity
                )
            }
            ElevatedCard(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.settings_title_other),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                SettingItemsOther()
            }
        }

        when {
            mainViewModel.openRadioDialog -> {

                mainViewModel.savedThemeString.value?.let { selectedTheme ->
                    DialogSettingRadioButton(
                        title = mainViewModel.title,
                        description = mainViewModel.description,
                        openDialog = mainViewModel.openRadioDialog,
                        selectedTheme = selectedTheme,
                        onDismiss = mainViewModel::closeRadioDialog,
                        onSave = { newTheme ->
                            mainViewModel.closeRadioDialog()
                            mainViewModel.saveTheme(newTheme)
                        }
                    )
                }
            }

            mainViewModel.openEditTextDialog -> {
                DialogSettingEditText(
                    title = mainViewModel.title,
                    description = mainViewModel.description,
                    placeholder = mainViewModel.placeholder,
                    openDialog = mainViewModel.openEditTextDialog,
                    onDismiss = {
                        mainViewModel.closeEditTextDialog(isSave = false)
                    },
                    onSave = { text ->
                        mainViewModel.closeEditTextDialog(isSave = true, text)
                    }
                )
            }
        }
    }
}


