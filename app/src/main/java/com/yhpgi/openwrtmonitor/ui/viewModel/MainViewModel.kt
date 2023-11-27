package com.yhpgi.openwrtmonitor.ui.viewModel

import android.app.Application
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.yhpgi.openwrtmonitor.domain.helper.MainUtils
import com.yhpgi.openwrtmonitor.domain.helper.repository.DataStoreRepository
import com.yhpgi.openwrtmonitor.domain.model.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainViewModel(application: Application) : AndroidViewModel(application = application) {

    private val repository = DataStoreRepository(application)

    val savedThemeString = repository.getThemeString.asLiveData()
    val savedIpString = repository.getIPString.asLiveData()
    val savedLuciPathString = repository.getLuciPathString.asLiveData()
    val savedClashString = repository.getClashString.asLiveData()

    fun saveTheme(newThemeString: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveThemeString(newThemeString)
    }

    private fun saveIPString(newIpAddress: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveIPString(newIpAddress)
    }

    private fun saveLuciPathString(newLuciPathString: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveLuciString(newLuciPathString)
        }

    private fun saveOpenClashString(newClashString: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveClashString(newClashString)
        }

    var openEditTextDialog by mutableStateOf(false)
    var openRadioDialog by mutableStateOf(false)

    var title by mutableIntStateOf(MainUtils.INT_DEFAULT_VALUE)
    var description by mutableIntStateOf(MainUtils.INT_DEFAULT_VALUE)
    var placeholder by mutableStateOf(MainUtils.STRING_BLANK)
    private var keyDialog by mutableIntStateOf(MainUtils.INT_DEFAULT_VALUE)

    var hostname: String by mutableStateOf(MainUtils.STRING_LOADING)
    var model: String by mutableStateOf(MainUtils.STRING_LOADING)
    var firmwareVersion: String by mutableStateOf(MainUtils.STRING_LOADING)
    var kernelVersion: String by mutableStateOf(MainUtils.STRING_LOADING)

    fun getSystemInformation() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val savedIpAddress = "192.168.10.1"
                val httpClient = OkHttpClient()
                val request = Request.Builder()
                    .url("http://$savedIpAddress/api.json")
                    .build()
                val response = httpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseString = response.body.string()
                    val gson = Gson()
                    val apiResponse = gson.fromJson(responseString, ApiResponse::class.java)

                    val devices = apiResponse.data
                    for (device in devices) {
                        hostname = device.hostname
                        model = device.model
                        firmwareVersion = device.release.version
                        kernelVersion = device.kernel
                    }
                } else {
                    hostname = MainUtils.STRING_DATA_NOT_FOUND
                    model = MainUtils.STRING_DATA_NOT_FOUND
                    firmwareVersion = MainUtils.STRING_DATA_NOT_FOUND
                    kernelVersion = MainUtils.STRING_DATA_NOT_FOUND
                }
            } catch (e: Exception) {
                hostname = MainUtils.STRING_ERROR
                model = MainUtils.STRING_ERROR
                firmwareVersion = MainUtils.STRING_ERROR
                kernelVersion = MainUtils.STRING_ERROR
            }
        }
    }

    fun openEditTextDialog(
        @StringRes title: Int,
        @StringRes description: Int,
        defaultValue: String,
        key: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            openEditTextDialog = true
            this@MainViewModel.title = title
            this@MainViewModel.description = description
            this@MainViewModel.placeholder = defaultValue
            keyDialog = key
        }
    }

    fun closeEditTextDialog(
        isSave: Boolean,
        newKeyValue: String = "value"
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            openEditTextDialog = false
            if (isSave) {
                when (keyDialog) {
                    0 -> saveIPString(newKeyValue)
                    1 -> saveLuciPathString(newKeyValue)
                    2 -> saveOpenClashString(newKeyValue)
                }
            }
        }
    }

    fun openRadioDialog(title: Int, description: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            openRadioDialog = true
            this@MainViewModel.title = title
            this@MainViewModel.description = description
        }
    }

    fun closeRadioDialog() {
        openRadioDialog = false
    }
}