package com.yhpgi.openwrtmonitor.ui.component.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R

@Composable
fun LayoutMainSystemInformation(
    hostname: String,
    model: String,
    firmwareVersion: String,
    kernelVersion: String
) {
    Card(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Header
                Text(
                    text = "System Information",
                    style = MaterialTheme.typography.titleLarge
                )

                // Isi
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.hostname),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        hostname,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.device),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        model,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.firmware_version),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        firmwareVersion,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.kernel_version),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        kernelVersion,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

