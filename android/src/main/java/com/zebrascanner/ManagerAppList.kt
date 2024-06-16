package com.zebrascanner

import android.content.Context
import android.content.Intent
import android.os.Bundle

class ManagerAppList {
  private val profiles = ArrayList<ZebraProfile>()
  val PROFILE_NAME = "Zebra Scanner"
  val PACKAGE_NAME = "com.example.certificatemanager"

  private var appZebraProfile = ZebraProfile()
  private var profilesNames = ArrayList<String>()
  private var myContext: Context? = null
  private var isRightProfile = false

  fun addProfile(profile: ZebraProfile) {
    val includes = profiles.find { item -> item.profileName == profile.profileName }

    if (includes == null) {
      profiles.add(profile)

      if (profile.profileName == PROFILE_NAME) {
        setAppZebraProfileConfig(profile)
      }

      if (profiles.size == profilesNames.size) {
        switchAppAssociation()
      }
    }
  }

  fun setProfileNameList(profileNameList: ArrayList<String>) {
    profilesNames = profileNameList
  }

  fun setContext(context: Context) {
    myContext = context
  }
  private fun setAppZebraProfileConfig(appProfile: ZebraProfile) {
    appZebraProfile = appProfile
  }
  private fun switchAppAssociation() {
    validateRightProfile()

    if (!isRightProfile) {
      removeAppFromWrongProfile()

      addAppOnRightProfile()
    }
  }
  private fun addAppOnRightProfile() {
    val rightProfileBundle = appZebraProfile.config
    val appList = getAppList(rightProfileBundle)

    val newAppList = appList.map { item ->
      createAppListBundle(item)
    }.toMutableList()

    val bundleApp = Bundle()
    bundleApp.putString("PACKAGE_NAME", PACKAGE_NAME)
    bundleApp.putStringArray("ACTIVITY_LIST", arrayOf("*"))
    newAppList.add(bundleApp)

    rightProfileBundle.putParcelableArray("APP_LIST", newAppList.toTypedArray())
    sendProfileData(rightProfileBundle)
  }
  private fun sendProfileData(bundle: Bundle) {
    val i = Intent()
    bundle.putString("CONFIG_MODE", "OVERWRITE")

    i.setAction("com.symbol.datawedge.api.ACTION")
    i.putExtra("com.symbol.datawedge.api.SET_CONFIG", bundle)

    myContext?.sendBroadcast(i)
  }

  private fun removeAppFromWrongProfile() {
    val profile = getProfile()

    if (profile != null) {
      val wrongProfileBundle = profile.config
      val appList = getAppList(wrongProfileBundle)

      val newAppList = appList.map { item ->
        createAppListBundle(item)
      }.toMutableList()

      wrongProfileBundle.putParcelableArray("APP_LIST", newAppList.toTypedArray())
      sendProfileData(wrongProfileBundle)
    }
  }

  private fun createAppListBundle(bundle: Bundle): Bundle {
    val packageName = bundle.getString("PACKAGE_NAME")
    val activities = bundle.getStringArrayList("ACTIVITY_LIST")
      ?.toTypedArray()

    bundle.putString("PACKAGE_NAME", packageName.toString())
    bundle.putStringArray("ACTIVITY_LIST", activities)
    return bundle
  }

  private fun getAppList(bundle: Bundle): ArrayList<Bundle> {
    val appList = bundle.getParcelableArrayList<Bundle>("APP_LIST")

    val newAppList = appList?.filter { item ->
      val _packageName = item.getString("PACKAGE_NAME").toString()
      _packageName != PACKAGE_NAME
    } ?: return ArrayList<Bundle>()

    return ArrayList(newAppList)
  }

  private fun getProfile(): ZebraProfile? {
    return profiles.find { item ->
      val result = findApp(item.config)
      result != null
    }
  }

  private fun validateRightProfile() {
    val result = findApp(appZebraProfile.config)
    isRightProfile = result != null
  }

  private fun findApp(bundle: Bundle): Bundle? {
    val appList = bundle.getParcelableArrayList<Bundle>("APP_LIST")
    return appList?.find { item ->
      val _packageName = item.getString("PACKAGE_NAME").toString()
      _packageName == PACKAGE_NAME
    }
  }
}
