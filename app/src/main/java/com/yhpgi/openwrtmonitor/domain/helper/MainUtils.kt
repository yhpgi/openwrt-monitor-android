package com.yhpgi.openwrtmonitor.domain.helper

import androidx.datastore.preferences.core.stringPreferencesKey

object MainUtils {
    const val STRING_ERROR = "Error"
    const val STRING_DATA_NOT_FOUND = "No Data Found"
    const val STRING_LOADING = "Loading…"
    const val STRING_BLANK = ""
    const val STRING_DEFAULT_THEME = "Follow system"

    const val DEFAULT_IP = "192.168.1.1"
    const val DEFAULT_LUCI_PATH = "cgi-bin/luci"
    const val DEFAULT_CLASH_PATH = ":9090/ui/yacd"
    const val INT_DEFAULT_VALUE = 0

    const val KEY_APP_SETTINGS = "app-settings"
    val KEY_APP_THEME_STRING = stringPreferencesKey("app-theme-string")
    val KEY_APP_IP_STRING = stringPreferencesKey("app-ip-string")
    val KEY_APP_LUCI_STRING = stringPreferencesKey("app-luci-string")
    val KEY_APP_CLASH_STRING = stringPreferencesKey("app-clash-string")
}