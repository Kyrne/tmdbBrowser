package com.m7019e.tmdbbrowser

import android.app.Application
import com.m7019e.tmdbbrowser.data.AppContainer
import com.m7019e.tmdbbrowser.data.DefaultAppContainer

class TMDBApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}