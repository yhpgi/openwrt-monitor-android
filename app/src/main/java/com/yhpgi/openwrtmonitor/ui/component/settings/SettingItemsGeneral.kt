package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@Composable
fun SettingItemsGeneral(mainViewModel: MainViewModel) {
    Column {
        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_default_ip),
            description = stringResource(R.string.settings_default_ip_description),
            savedValue = mainViewModel.savedIpString,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_default_ip,
                    description = R.string.settings_default_ip_description,
                    defaultValue = mainViewModel.savedIpString,
                    key = 0
                )
            }
        )
        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_luci_path),
            description = stringResource(R.string.settings_luci_path_description),
            savedValue = mainViewModel.savedLuciPathString,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_luci_path,
                    description = R.string.settings_luci_path_description,
                    defaultValue = mainViewModel.savedLuciPathString,
                    key = 1
                )
            }
        )
        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_openclash_port),
            description = stringResource(R.string.settings_openclash_port_description),
            savedValue = mainViewModel.savedClashString,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_openclash_port,
                    description = R.string.settings_openclash_port_description,
                    defaultValue = mainViewModel.savedClashString,
                    key = 2
                )
            }
        )
    }
}