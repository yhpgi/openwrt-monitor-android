package com.yhpgi.openwrtmonitor.domain.helper.datastore

import com.tencent.mmkv.MMKV
import com.yhpgi.openwrtmonitor.domain.helper.MainUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MmkvUtils {

    private val settingsMmkv = MMKV.mmkvWithID(MainUtils.KEY_APP_SETTINGS, MMKV.MULTI_PROCESS_MODE)

    val getThemeString: String = settingsMmkv.decodeString(MainUtils.KEY_APP_THEME_STRING)
        ?: MainUtils.STRING_DEFAULT_THEME

    fun saveThemeString(setSelectedThemeString: String) {
        CoroutineScope(Dispatchers.IO).launch {
            settingsMmkv.encode(MainUtils.KEY_APP_THEME_STRING, setSelectedThemeString)
            settingsMmkv.async()
        }
    }

    val getIPString: String = settingsMmkv.decodeString(MainUtils.KEY_APP_IP_STRING)
        ?: MainUtils.DEFAULT_IP

    fun saveIPString(newIP: String) {
        CoroutineScope(Dispatchers.IO).launch {
            settingsMmkv.encode(MainUtils.KEY_APP_IP_STRING, newIP)
            settingsMmkv.async()
        }
    }

    val getLuciPathString: String = settingsMmkv.decodeString(MainUtils.KEY_APP_LUCI_STRING)
        ?: MainUtils.DEFAULT_LUCI_PATH

    fun saveLuciString(newLuciPath: String) {
        CoroutineScope(Dispatchers.IO).launch {
            settingsMmkv.encode(MainUtils.KEY_APP_LUCI_STRING, newLuciPath)
            settingsMmkv.async()
        }
    }

    val getClashString: String = settingsMmkv.decodeString(MainUtils.KEY_APP_CLASH_STRING)
        ?: MainUtils.DEFAULT_CLASH_PATH

    fun saveClashString(newOpenClashPath: String) {
        CoroutineScope(Dispatchers.IO).launch {
            settingsMmkv.encode(MainUtils.KEY_APP_CLASH_STRING, newOpenClashPath)
            settingsMmkv.async()
        }
    }

}