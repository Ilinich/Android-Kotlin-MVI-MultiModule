package com.begoml.tools.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.telephony.TelephonyManager
import java.lang.Long
import java.util.*

@SuppressLint("MissingPermission")
fun Context.getAndroidId(): String {

    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        return telephonyManager.imei
    } else {
        val uri = Uri.parse("content://com.google.android.gsf.gservices")

        var androidId = UUID.randomUUID().toString()

        val params = arrayOf("android_id")
        val cursor = contentResolver?.query(uri, null, null, params, null)

        if (cursor != null && (!cursor.moveToFirst() || cursor.columnCount < 2)) {
            cursor.close()
        } else if (cursor != null) {
            androidId = try {
                Long.toHexString(Long.parseLong(cursor.getString(1)))
            } catch (e: NumberFormatException) {
                emptyString()
            } finally {
                cursor.close()
            }
        }

        return androidId
    }
}

@SuppressLint("MissingPermission")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    } else {
        connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}