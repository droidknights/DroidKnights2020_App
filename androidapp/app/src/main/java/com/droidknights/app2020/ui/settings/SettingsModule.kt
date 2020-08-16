package com.droidknights.app2020.ui.settings

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.di.annotation.FragmentScoped
import com.droidknights.app2020.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class SettingsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMypageFragment(): SettingsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindMypageFragmentViewModel(viewModel: SettingsViewModel): ViewModel
}