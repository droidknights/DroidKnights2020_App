package com.droidknights.app2020.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by jiyoung on 29/11/2019
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DKViewModelFactory): ViewModelProvider.Factory
}