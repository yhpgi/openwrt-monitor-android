package com.yhpgi.openwrtmonitor.ui.component.luci

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopProgressBar(visible: Boolean = true) {
    if (visible) {
        Row(verticalAlignment = Alignment.Top) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(MaterialTheme.colorScheme.background),
                trackColor = MaterialTheme.colorScheme.background,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}