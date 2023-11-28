package com.yhpgi.openwrtmonitor.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yhpgi.openwrtmonitor.R
import com.yhpgi.openwrtmonitor.domain.helper.accompanist.web.AccompanistWebViewClient
import com.yhpgi.openwrtmonitor.domain.helper.accompanist.web.LoadingState
import com.yhpgi.openwrtmonitor.domain.helper.accompanist.web.WebView
import com.yhpgi.openwrtmonitor.domain.helper.accompanist.web.rememberSaveableWebViewState
import com.yhpgi.openwrtmonitor.domain.helper.accompanist.web.rememberWebViewNavigator

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LuciScreen(
    luciUrl: String,
    internetConfigurationIsChanged: Boolean,
    onBackPressed: () -> Unit,
    onPageLoadedAfterValueChanged: () -> Unit
) {
    val state = rememberSaveableWebViewState()
    val navigator = rememberWebViewNavigator()

    LaunchedEffect(navigator) {
        val bundle = state.viewState
        if (internetConfigurationIsChanged || bundle == null) {
            navigator.loadUrl(luciUrl)
            onPageLoadedAfterValueChanged()
        }
    }

    val subtitle by remember(state.lastLoadedUrl) {
        mutableStateOf(state.lastLoadedUrl)
    }
    val title by remember(state.pageTitle) {
        mutableStateOf(state.pageTitle)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = title ?: stringResource(id = R.string.navbar_luci),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = subtitle ?: "http://",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (navigator.canGoBack) {
                                navigator.navigateBack()
                            } else {
                                onBackPressed()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                },
                actions = {
                    Row {
                        if (navigator.canGoForward) {
                            IconButton(
                                onClick = {
                                    navigator.navigateForward()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Forward"
                                )
                            }
                        }
                        IconButton(
                            onClick = {
                                navigator.reload()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reload"
                            )
                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            ) {


                val webClient = remember {
                    object : AccompanistWebViewClient() {}
                }

                WebView(
                    state = state,
                    modifier = Modifier.fillMaxSize(),
                    navigator = navigator,
                    onCreated = { webView ->
                        webView.settings.javaScriptEnabled = true
                    },
                    client = webClient
                )
            }
            val loadingState = state.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = loadingState.progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}

