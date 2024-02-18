package com.scanner

import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class ScannerModule(reactContext: ReactApplicationContext?) : ReactContextBaseJavaModule(reactContext),
    LifecycleEventListener{
    private val _reactContext: ReactApplicationContext? = reactContext
    private var myBroadcastReceiver = ScannerReceiveBroadcast(reactContext)
    private val _filter: IntentFilter =  IntentFilter()
    private var _id: String? = null

    init {
        _filter.addCategory(Intent.CATEGORY_DEFAULT)
        _filter.addAction("com.dwbasicintent1.ACTION")
        _reactContext?.registerReceiver(myBroadcastReceiver, _filter)
        _reactContext?.addLifecycleEventListener(this)
    }

    override fun getName(): String {
        return "ScannerModule"
    }

    private fun onRegisterReceiver() {
        myBroadcastReceiver.id = _id
        _reactContext?.registerReceiver(myBroadcastReceiver, _filter)
    }

    @ReactMethod()
    fun onInit(id: String) {
        _id = id
        onRegisterReceiver()
    }

    override fun onHostResume() {
        onRegisterReceiver()
        Log.d("REMUSE123", "Olar")
    }

    override fun onHostPause() {
        _reactContext?.unregisterReceiver(myBroadcastReceiver)
        Log.d("PAUSE", "Bye")
    }

    override fun onHostDestroy() {
        _reactContext?.unregisterReceiver(myBroadcastReceiver)
    }
}