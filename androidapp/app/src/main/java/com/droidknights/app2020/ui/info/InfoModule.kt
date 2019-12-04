package com.droidknights.app2020.ui.info

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
internal abstract class InfoModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeInfoFragment(): InfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    abstract fun bindInfoFragmentViewModel(viewModel: InfoViewModel): ViewModel
}