package com.droidknights.app2020.di

import android.content.Context
import com.droidknights.app2020.MainApplication
import com.droidknights.app2020.base.DefaultDispatcherProvider
import com.droidknights.app2020.base.DispatcherProvider
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

    @Provides
    fun provideDispatchers(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}