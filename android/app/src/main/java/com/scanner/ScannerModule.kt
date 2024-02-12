package com.scanner

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class ScannerModule(reactContext: ReactApplicationContext?) : ReactContextBaseJavaModule(reactContext),
    LifecycleEventListener {
    private val _reactContext: ReactApplicationContext? = reactContext

    private val scannerModuleReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action


            if (action == intent.getStringExtra("com.dwbasicintent1.ACTION")) {
                //  Received a barcode scan
                try {
                    displayScanResult(intent, "via Broadcast")
                } catch (e: Exception) {
                    //  Catch if the UI does not exist when we receive the broadcast
                }
            }
//            if (intent.action == Intent.ACTION_HEADSET_PLUG) {
//                val plugged = intent.getIntExtra("state", 0) == 1
//                val message = if (plugged) "Headset plugged in" else "Headset plugged out"
//                Toast.makeText(_reactContext, message, Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private fun displayScanResult(initiatingIntent: Intent, howDataReceived: String) {
        val decodedSource = initiatingIntent.getStringExtra("com.symbol.datawedge.source")
        val decodedData = initiatingIntent.getStringExtra("com.symbol.datawedge.label_type")
        val decodedLabelType = initiatingIntent.getStringExtra("com.symbol.datawedge.data_string")

        Log.d("DATA", decodedData.toString())
    }
    override fun getName(): String {
        return "ScannerModule"
    }


    @ReactMethod()
    fun test(promise: Promise) {
        return try {
            promise.resolve("Olar")
        } catch (e: Error) {
            promise.reject("Error")
        }
    }

    override fun onHostResume() {}

    override fun onHostPause() {}

    override fun onHostDestroy() {
        _reactContext!!.unregisterReceiver(scannerModuleReceiver)
    }

}