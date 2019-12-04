package com.droidknights.app2020.ui.schedule

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.di.annotation.FragmentScoped
import com.droidknights.app2020.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by jiyoung on 04/12/2019
 */
@Module
internal abstract class ScheduleModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeScheduleFragment(): ScheduleFragment

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleFragmentViewModel(viewModel: ScheduleViewModel): ViewModel
}