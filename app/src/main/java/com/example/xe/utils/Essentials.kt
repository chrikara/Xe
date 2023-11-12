package com.example.xe.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

const val BASE_URL = "https://4ulq3vb3dogn4fatjw3uq7kqby0dweob.lambda-url.eu-central-1.on.aws/"
const val JSON_SAMPLE_FROM_XE = "[{\"placeId\":\"ChIJY3lRwZz6nxQRuEDWtg1FTgg\",\"mainText\":\"Nafplio\",\"secondaryText\":\"Ελλάδα\"},{\"placeId\":\"EiJOYWZwbGlvdSwgzpHOuM6uzr3OsSwgzpXOu867zqzOtM6xIi4qLAoUChIJcVLCqNe8oRQRfILLtu2PrC0SFAoSCfFDcAYfvaEUEd2NZnZFNTYn\",\"mainText\":\"Nafpliou\",\"secondaryText\":\"Αθήνα, Ελλάδα\"},{\"placeId\":\"EilOYWZwbGlvIEFyZ29saXMsIM6RzrjOrs69zrEsIM6VzrvOu86szrTOsSIuKiwKFAoSCStX8j6qoqEUEZYp8o4MXa_mEhQKEgnxQ3AGH72hFBHdjWZ2RTU2Jw\",\"mainText\":\"Nafplio Argolis\",\"secondaryText\":\"Αθήνα, Ελλάδα\"},{\"placeId\":\"EiJOYWZwbGlvdSwgzorOu865zr_OvSwgzpXOu867zqzOtM6xIi4qLAoUChIJPdAlBRmjoRQR2WqMpFVzeYcSFAoSCWXz_Aqoo6EUEaB5ueIsvQAE\",\"mainText\":\"Nafpliou\",\"secondaryText\":\"Ίλιον, Ελλάδα\"},{\"placeId\":\"EjBOYWZwbGlvdSBOZWFzIEtpb3UsIM6dzrHPjc-AzrvOuc6_LCDOlc67zrvOrM60zrEiLiosChQKEgljrsJDhfqfFBEHmuP71jJcohIUChIJY3lRwZz6nxQRuEDWtg1FTgg\",\"mainText\":\"Nafpliou Neas Kiou\",\"secondaryText\":\"Ναύπλιο, Ελλάδα\"}]"
const val DELAY = 500L

fun hasInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return true
        }
    }
    return false
}