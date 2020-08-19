package com.droidknights.app2020.ui.settings

import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.SettingsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment :
    BaseFragment<SettingsViewModel, SettingsFragmentBinding>(
        R.layout.settings_fragment,
        SettingsViewModel::class.java
    ) {
    //TODO : 내 세션 북마크 (즐겨찾기 기능)
    //TODO : DarkTheme on/off
    //TODO : license 페이지
}