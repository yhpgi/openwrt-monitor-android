package com.yhpgi.openwrtmonitor.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainDataUsage
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainDeviceMonitor
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainInternetStatus
import com.yhpgi.openwrtmonitor.ui.component.main.LayoutMainSystemInformation
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel = viewModel()) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                mainViewModel.hostname,
                mainViewModel.model,
                mainViewModel.firmwareVersion,
                mainViewModel.kernelVersion
            )
            mainViewModel.getSystemInformation()
            LayoutMainDeviceMonitor()

        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen()
}

