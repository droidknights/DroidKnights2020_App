package com.droidknights.app2020.di

import com.droidknights.app2020.base.DefaultDispatcherProvider
import com.droidknights.app2020.base.DispatcherProvider
import dagger.Binds
import dagger.Module

/**
 * Created by jiyoung on 29/11/2019
 */
@Module
internal interface AppModule {
    @Binds
    fun bindDispatchers(dispatcher: DefaultDispatcherProvider): DispatcherProvider
}