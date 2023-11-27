package com.yhpgi.openwrtmonitor.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.yhpgi.openwrtmonitor.domain.helper.webview.OpenClashWebViewHelper
import com.yhpgi.openwrtmonitor.ui.activity.MainActivity
import com.yhpgi.openwrtmonitor.ui.component.luci.TopProgressBar
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenClashScreen(
    mainViewModel: MainViewModel = viewModel(),
    mainActivity: MainActivity
) {

    var openClashUrl by rememberSaveable {
        mutableStateOf(mainViewModel.openClashUrl)
    }
    var openClashTitle by rememberSaveable {
        mutableStateOf(mainViewModel.openClashTitle)
    }

    if (mainViewModel.openClashWebViewHelper == null) {
        mainViewModel.openClashWebViewHelper =
            OpenClashWebViewHelper(LocalContext.current).apply {
                var savedIpAddress = mainViewModel.savedIpString.value
                mainViewModel.savedIpString.observe(mainActivity) {
                    savedIpAddress = it
                }
                var savedClashPath = mainViewModel.savedClashString.value
                mainViewModel.savedClashString.observe(mainActivity) {
                    savedClashPath = it
                }
                loadUrl("http://$savedIpAddress$savedClashPath")
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = mainViewModel.openClashTitle,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = mainViewModel.openClashUrl,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { mainViewModel.refreshOpenClashView() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "refresh"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            if (mainViewModel.openClashWebViewHelper?.loadStatus() != true) TopProgressBar(visible = true)
            else TopProgressBar(visible = false)

            mainViewModel.openClashWebViewHelper?.let {

                AndroidView(
                    factory = { _ ->
                        it.openClashWebView
                    }
                )
                mainViewModel.getOpenClashUrl()
                mainViewModel.getOpenClashTitle()
                openClashUrl = mainViewModel.openClashUrl
                openClashTitle = mainViewModel.openClashTitle
            }
        }
    }
}

