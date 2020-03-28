package com.droidknights.app2020.ui.info.sponsor

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.di.annotation.FragmentScoped
import com.droidknights.app2020.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class SponsorModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSponsorFragment(): SponsorFragment

    @Binds
    @IntoMap
    @ViewModelKey(SponsorViewModel::class)
    abstract fun bindSponsorFragmentViewModel(viewModel: SponsorViewModel): ViewModel
}