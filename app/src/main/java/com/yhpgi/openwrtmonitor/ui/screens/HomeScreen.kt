package com.yhpgi.openwrtmonitor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainDataUsage
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainDeviceMonitor
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainInternetStatus
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainSystemInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    hostname: String,
    model: String,
    firmwareVersion: String,
    kernelVersion: String,
    getSystemInfo: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(key1 = Unit) {
        getSystemInfo()
    }
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.title_dashboard)
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(it)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            LayoutMainDataUsage()
            LayoutMainInternetStatus()
            LayoutMainSystemInformation(
                hostname,
                model,
                firmwareVersion,
                kernelVersion
            )
            LayoutMainDeviceMonitor()
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(
        "hostname",
        "model",
        "firmwareVersion",
        "kernelVersion"
    ) { }
}

