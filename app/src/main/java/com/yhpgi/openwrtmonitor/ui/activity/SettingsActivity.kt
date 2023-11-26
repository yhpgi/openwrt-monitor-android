package com.yhpgi.openwrtmonitor.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.screens.SettingsScreen
import com.yhpgi.openwrtmonitor.ui.theme.OpenWrtMonitorTheme
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

class SettingsActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appTheme = when (mainViewModel.savedThemeString) {
                getString(R.string.setting_theme_follow_system) -> isSystemInDarkTheme()
                getString(R.string.setting_theme_dark) -> true
                else -> false
            }

            OpenWrtMonitorTheme(darkTheme = appTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingsScreen(mainViewModel)
                }
            }
        }
    }
}
