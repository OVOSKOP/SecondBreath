package com.ovoskop.secondbreath

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.secondbreath.ui.adapters.BluetoothListAdapter
import com.ovoskop.secondbreath.ui.dialogs.DeviceDialog
import com.ovoskop.secondbreath.utils.App
import com.ovoskop.secondbreath.utils.classes.BluetoothListDevice
import com.ovoskop.secondbreath.utils.dpToPx
import com.ovoskop.secondbreath.utils.hideSystemUI

class MainActivity : AppCompatActivity() {

    private lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app = applicationContext as App

        if (!app.showed) {
            showBluetoothDevice()
        }
    }

    override fun onResume() {
        super.onResume()

        println("RESUMED")

        hideSystemUI()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus) {
            hideSystemUI()
        }
    }

    fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= 30) {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            }
        } else {
            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    private fun showBluetoothDevice() {
        val dialog = DeviceDialog()

        dialog.show(supportFragmentManager, "devices")
        app.showed = true
    }
}