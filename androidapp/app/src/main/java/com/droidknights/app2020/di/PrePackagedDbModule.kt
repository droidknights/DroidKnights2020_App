package com.droidknights.app2020.di

import android.content.Context
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.droidknights.app2020.db.prepackage.PrePackagedDbImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class PrePackagedDbModule {

    @Singleton
    @Provides
    fun providePrePackagedDb(@ApplicationContext application: Context, gson: Gson): PrePackagedDb =
        PrePackagedDbImpl(application, gson, assetsName = "sessions.json")
}
