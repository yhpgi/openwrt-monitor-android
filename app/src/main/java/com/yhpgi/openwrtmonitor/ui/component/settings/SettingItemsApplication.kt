package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@Composable
fun SettingItemsApplication(mainViewModel: MainViewModel) {
    Column {
        SettingItem(
            icon = Icons.TwoTone.Face,
            title = stringResource(R.string.settings_app_theme),
            description = stringResource(R.string.settings_app_theme_description),
            savedValue = mainViewModel.savedThemeString,
            onClick = {
                mainViewModel.openRadioDialog(
                    title = R.string.settings_app_theme,
                    description = R.string.settings_app_theme_description
                )
            }
        )
    }
}
