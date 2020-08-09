package com.droidknights.app2020.di

import com.droidknights.app2020.base.DefaultDispatcherProvider
import com.droidknights.app2020.base.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by jiyoung on 29/11/2019
 */
@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()
}