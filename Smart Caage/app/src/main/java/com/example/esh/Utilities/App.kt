package com.example.esh.Utilities

import android.app.Application
import com.example.esh.Interface.AppComponent
import com.example.esh.Dagger.AppModule
import com.example.esh.Interface.DaggerAppComponent

class App : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    protected fun initDagger(application: com.example.esh.Utilities.App): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun getAppComponent(): AppComponent? {
        return this.appComponent
    }
}