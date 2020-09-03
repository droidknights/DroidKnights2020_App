package com.droidknights.app2020.di

import com.droidknights.app2020.base.DefaultDispatcherProvider
import com.droidknights.app2020.base.DispatcherProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by jiyoung on 29/11/2019
 */
@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()
}