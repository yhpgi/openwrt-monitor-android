package com.yhpgi.openwrtmonitor.ui.component.main

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.component.luci.MainCircularProgressBar

@Composable
fun LayoutMainDeviceMonitor() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        MainCircularProgressBar(
            percentage = 0.35f,
            title = stringResource(R.string.device_temperature),
            textModifier = "°"
        )
        MainCircularProgressBar(
            percentage = 0.12f,
            title = stringResource(R.string.cpu_usage),
            textModifier = "%"
        )
        MainCircularProgressBar(
            percentage = 0.5f,
            title = stringResource(R.string.ram_usage),
            textModifier = "%"
        )
    }
}
