package com.example.movieapp

import android.app.Application
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.movieapp.di.AppComponent
import com.example.movieapp.di.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

open class MyApplication : Application() {

    //test branch for the see pr
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    @ExperimentalCoroutinesApi
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}