package com.scanner

import android.content.Intent
import android.content.IntentFilter
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class ScannerModule(reactContext: ReactApplicationContext?) : ReactContextBaseJavaModule(reactContext),
    LifecycleEventListener{
    private val _reactContext: ReactApplicationContext? = reactContext
    private var myBroadcastReceiver = ScannerReceiveBroadcast(reactContext)
    override fun getName(): String {
        return "ScannerModule"
    }

    @ReactMethod()
    fun onInit(id: String) {
        myBroadcastReceiver.id = id
        val filter = IntentFilter()
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        filter.addAction("com.dwbasicintent1.ACTION")
        _reactContext?.registerReceiver(myBroadcastReceiver, filter)
    }

    override fun onHostResume() {}

    override fun onHostPause() {}

    override fun onHostDestroy() {
    }
}