package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.runtime.Composable
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@Composable
fun SettingItemsOther(mainViewModel: MainViewModel) {
    Column{
        SettingItem(
            icon = Icons.TwoTone.Face,
            title = "About",
            description = "About app",
            savedValue = "App version: 0.0.3",
            onClick = {

            }
        )
    }
}

