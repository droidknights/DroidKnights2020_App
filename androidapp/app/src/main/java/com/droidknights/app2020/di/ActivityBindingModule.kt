package com.droidknights.app2020.di

import com.droidknights.app2020.MainActivity
import com.droidknights.app2020.di.annotation.ActivityScoped
import com.droidknights.app2020.ui.main.HomeModule
import com.droidknights.app2020.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jiyoung on 29/11/2019
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            HomeModule::class
        ]
    )
    internal abstract fun getMainActivity() : MainActivity
}