package com.yhpgi.openwrtmonitor.ui.component.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R

@Composable
fun LayoutMainInternetStatus() {
    OutlinedCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.internet_status),
                fontWeight = FontWeight.Bold
            )
            Text(text = stringResource(R.string.connected))
        }
    }
}
