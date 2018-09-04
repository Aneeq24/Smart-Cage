package com.example.esh.Interface

import com.example.esh.Activity.StartActivity
import com.example.esh.Activity.MainActivity
import com.example.esh.Dagger.AppModule
import com.example.esh.Dagger.PreferenceModule
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(AppModule::class, PreferenceModule::class))
interface AppComponent {

    fun inject(infoActivity: StartActivity)
    fun inject(mainActivity: MainActivity)
}
