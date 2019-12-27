package com.droidknights.app2020.di

import android.content.Context
import com.droidknights.app2020.MainApplication
import dagger.Module
import dagger.Provides

/**
 * Created by jiyoung on 29/11/2019
 */
@Module
class AppModule {
    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }
}