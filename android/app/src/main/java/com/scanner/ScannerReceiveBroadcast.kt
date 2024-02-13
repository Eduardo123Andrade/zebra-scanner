package com.scanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.modules.core.DeviceEventManagerModule

class ScannerReceiveBroadcast(reactContext: ReactApplicationContext?) : BroadcastReceiver() {
    private val _reactContext: ReactApplicationContext = reactContext!!
    var id: String? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == "com.dwbasicintent1.ACTION") {
            try {
                displayScanResult(intent, "via Broadcast")
            } catch (e: Exception) {
                //  Catch if the UI does not exist when we receive the broadcast
            }
        }
    }

    private fun displayScanResult(initiatingIntent: Intent, howDataReceived: String) {
        val decodedSource = initiatingIntent.getStringExtra("com.symbol.datawedge.source")
        val decodedData = initiatingIntent.getStringExtra("com.symbol.datawedge.data_string")
        val decodedLabelType = initiatingIntent.getStringExtra("com.symbol.datawedge.label_type")
        val event = Arguments.createMap()
        val key = "onScanner-$id"
        Log.d("RECEIVED_BROADCAST", decodedData.toString())

        _reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(key, decodedData.toString())

    }
}