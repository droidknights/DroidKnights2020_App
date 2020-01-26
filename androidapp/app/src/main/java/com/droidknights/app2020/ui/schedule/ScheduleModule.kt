package com.droidknights.app2020.ui.schedule

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.di.annotation.FragmentScoped
import com.droidknights.app2020.di.annotation.ViewModelKey
import com.droidknights.app2020.ui.schedule.detail.SessionDetailFragment
import com.droidknights.app2020.ui.schedule.detail.SessionDetailViewModel
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

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSessionDetailFragment(): SessionDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(SessionDetailViewModel::class)
    abstract fun bindSessionDetailFragmentViewModel(viewModel: SessionDetailViewModel): ViewModel
}