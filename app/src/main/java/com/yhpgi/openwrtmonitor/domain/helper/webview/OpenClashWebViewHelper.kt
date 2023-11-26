package com.yhpgi.openwrtmonitor.domain.helper.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class OpenClashWebViewHelper(context: Context) {
    var openClashWebView: WebView = WebView(context)
    var isLoaded by mutableStateOf(false)

    private var webChromeClient = object : WebChromeClient() {
        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        }
    }

    private var webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            isLoaded = false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            isLoaded = true
        }
    }

    init {
        openClashWebView.layoutParams = ViewGroup.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings = openClashWebView.settings
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.allowFileAccess = true
        webSettings.setSupportZoom(false)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true
        webSettings.mediaPlaybackRequiresUserGesture = false

        setupWebChromeClient()
        setupWebViewClient()
    }

    private fun setupWebChromeClient() {
        openClashWebView.webChromeClient = webChromeClient
    }

    private fun setupWebViewClient() {
        openClashWebView.webViewClient = webViewClient
    }

    fun loadUrl(url: String) {
        openClashWebView.loadUrl(url)
    }

    fun refreshWebView() {
        openClashWebView.reload()
    }


    fun getTitle(): String? {
        return openClashWebView.title
    }

    fun getUrl(): String? {
        return openClashWebView.url
    }

    fun loadStatus(): Boolean {
        return isLoaded
    }
}