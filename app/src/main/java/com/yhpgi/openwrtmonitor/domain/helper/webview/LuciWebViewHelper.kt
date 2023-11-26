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

class LuciWebViewHelper(context: Context) {
    var luciWebView: WebView = WebView(context)
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

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            return super.onConsoleMessage(consoleMessage)
        }
    }

    private var webViewClient = object : WebViewClient() {
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            isLoaded = false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            isLoaded = true
        }
    }

    init {
        luciWebView.layoutParams = ViewGroup.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings = luciWebView.settings
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
        WebView.setWebContentsDebuggingEnabled(true)

        setupWebChromeClient()
        setupWebViewClient()
    }

    private fun setupWebChromeClient() {
        luciWebView.webChromeClient = webChromeClient
    }

    private fun setupWebViewClient() {
        luciWebView.webViewClient = webViewClient
    }

    fun loadUrl(url: String) {
        luciWebView.loadUrl(url)
    }

    fun refreshWebView() {
        luciWebView.reload()
    }

    fun getTitle(): String? {
        return luciWebView.title
    }

    fun getUrl(): String? {
        return luciWebView.url
    }

    fun loadStatus(): Boolean {
        return isLoaded
    }
}