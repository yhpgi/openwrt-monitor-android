package com.yhpgi.openwrtmonitor.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yhpgi.openwrtmonitor.domain.helper.webview.LuciWebViewHelper
import com.yhpgi.openwrtmonitor.ui.component.luci.TopProgressBar
import com.yhpgi.openwrtmonitor.ui.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuciScreen(mainViewModel: MainViewModel = viewModel()) {

    var luciTitle by rememberSaveable {
        mutableStateOf(mainViewModel.luciTitle)
    }
    var luciUrl by rememberSaveable {
        mutableStateOf(mainViewModel.luciUrl)
    }

    if (mainViewModel.luciWebViewHelper == null) {
        mainViewModel.luciWebViewHelper =
            LuciWebViewHelper(LocalContext.current).apply {
                val savedIpAddress = mainViewModel.savedIpString
                val savedLuciPath = mainViewModel.savedLuciPathString
                loadUrl("http://$savedIpAddress/$savedLuciPath")
            }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = mainViewModel.luciTitle,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = mainViewModel.luciUrl,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { mainViewModel.refreshLuciView() }) {
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
            if (mainViewModel.luciWebViewHelper?.loadStatus() != true) TopProgressBar(visible = true)
            else TopProgressBar(visible = false)

            mainViewModel.luciWebViewHelper?.let {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    factory = { _ ->
                        it.luciWebView
                    }
                )
                mainViewModel.getLuciUrl()
                mainViewModel.getLuciWebTitle()
                luciUrl = mainViewModel.luciUrl
                luciTitle = mainViewModel.luciTitle
            }
        }
    }
}

@Preview
@Composable
fun LuciScreenPreview() {
    LuciScreen()
}
