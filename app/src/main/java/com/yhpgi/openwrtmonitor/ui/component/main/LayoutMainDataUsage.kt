package com.yhpgi.openwrtmonitor.ui.component.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayoutMainDataUsage() {
    ElevatedCard(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Data usage",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .align(Alignment.Bottom),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "4.4 GB",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(text = "Used Today")

                }
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .padding(start = 8.dp)
                        .align(Alignment.Bottom),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "400.4 GB",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.align(Alignment.End)
                    )
                    Text(
                        text = "Used This Month",
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }

}