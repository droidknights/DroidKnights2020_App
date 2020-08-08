package com.droidknights.app2020.di

import com.droidknights.app2020.base.DefaultDispatcherProvider
import com.droidknights.app2020.base.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by jiyoung on 29/11/2019
 */
@InstallIn(ApplicationComponent::class)
@Module
internal interface AppModule {
    @Binds
    fun bindDispatchers(dispatcher: DefaultDispatcherProvider): DispatcherProvider
}