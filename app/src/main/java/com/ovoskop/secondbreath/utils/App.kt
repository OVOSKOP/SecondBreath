package com.ovoskop.secondbreath.utils

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class App : Application() {
    var mode: Mode = Mode.DAY
        set(value) {
            field = value

            if (value == Mode.DAY) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

    var showed = false

    override fun onCreate() {
        super.onCreate()

        println("CREATED")

        val shared = getSharedPreferences(
            "mode", Context.MODE_PRIVATE)

        mode = if (shared.getBoolean("night", false)) {
            Mode.NIGHT
        } else {
            Mode.DAY
        }
    }

}