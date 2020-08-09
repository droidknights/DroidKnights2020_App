package com.droidknights.app2020.di

import com.droidknights.app2020.MainApplication
import com.droidknights.app2020.db.prepackage.PrePackagedDb
import com.droidknights.app2020.db.prepackage.PrePackagedDbImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrePackagedDbModule {
    @Singleton
    @Provides
    fun providePrePackagedDb(application: MainApplication): PrePackagedDb =
        PrePackagedDbImpl(application, assetsName = "sessions.json")
}
