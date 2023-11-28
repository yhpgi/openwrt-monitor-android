package com.yhpgi.openwrtmonitor.ui.component.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@Composable
fun SettingItemsOther() {
    Column {
        SettingItemExpandable(
            title = "About app",
            description = "App Version : 0.0.4\n"
                    + "Dev: Yogi Hermawan",
        )
    }
}

