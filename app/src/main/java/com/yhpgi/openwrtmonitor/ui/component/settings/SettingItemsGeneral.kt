package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.domain.helper.repository.MainRepository
import com.yhpgi.openwrtmonitor.ui.activity.MainActivity
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@Composable
fun SettingItemsGeneral(mainViewModel: MainViewModel, mainActivity: MainActivity) {
    Column {
        var defaultIp by rememberSaveable {
            mutableStateOf(MainRepository.DEFAULT_IP)
        }
        mainViewModel.savedIpString.observe(mainActivity) {
            defaultIp = it
        }

        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_default_ip),
            description = stringResource(R.string.settings_default_ip_description),
            savedValue = defaultIp,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_default_ip,
                    description = R.string.settings_default_ip_description,
                    defaultValue = defaultIp,
                    key = 0
                )
            }
        )

        var defaultLuciPath by rememberSaveable {
            mutableStateOf(MainRepository.DEFAULT_LUCI_PATH)
        }
        mainViewModel.savedLuciPathString.observe(mainActivity) {
            defaultLuciPath = it
        }

        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_luci_path),
            description = stringResource(R.string.settings_luci_path_description),
            savedValue = defaultLuciPath,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_luci_path,
                    description = R.string.settings_luci_path_description,
                    defaultValue = defaultLuciPath,
                    key = 1
                )
            }
        )

        var defaultClashPath by rememberSaveable {
            mutableStateOf(MainRepository.DEFAULT_CLASH_PATH)
        }
        mainViewModel.savedClashString.observe(mainActivity) {
            defaultClashPath = it
        }

        SettingItem(
            icon = Icons.TwoTone.AccountCircle,
            title = stringResource(R.string.settings_openclash_port),
            description = stringResource(R.string.settings_openclash_port_description),
            savedValue = defaultClashPath,
            onClick = {
                mainViewModel.openEditTextDialog(
                    title = R.string.settings_openclash_port,
                    description = R.string.settings_openclash_port_description,
                    defaultValue = defaultClashPath,
                    key = 2
                )
            }
        )
    }
}