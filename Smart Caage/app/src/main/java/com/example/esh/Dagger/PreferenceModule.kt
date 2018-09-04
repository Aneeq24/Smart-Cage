package com.example.esh.Dagger

import android.content.Context
import com.example.esh.Utilities.Pref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    internal fun providePref(context: Context): Pref {
        return Pref(context)
    }
}