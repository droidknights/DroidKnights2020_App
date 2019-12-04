package com.droidknights.app2020.di

import com.droidknights.app2020.MainActivity
import com.droidknights.app2020.di.annotation.ActivityScoped
import com.droidknights.app2020.ui.info.InfoModule
import com.droidknights.app2020.ui.main.HomeModule
import com.droidknights.app2020.ui.main.MainActivityModule
import com.droidknights.app2020.ui.schedule.ScheduleModule
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
            HomeModule::class,
            ScheduleModule::class,
            InfoModule::class
        ]
    )
    internal abstract fun getMainActivity() : MainActivity
}