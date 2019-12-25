package com.droidknights.app2020.ui.mypage

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.di.annotation.FragmentScoped
import com.droidknights.app2020.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MypageModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMypageFragment(): MypageFragment

    @Binds
    @IntoMap
    @ViewModelKey(MypageViewModel::class)
    abstract fun bindMypageFragmentViewModel(viewModel: MypageViewModel): ViewModel
}