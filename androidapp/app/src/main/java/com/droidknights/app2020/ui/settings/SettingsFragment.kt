package com.droidknights.app2020.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.SettingsFragmentBinding
import com.droidknights.app2020.util.eventObserve
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, SettingsFragmentBinding>(
    R.layout.settings_fragment,
    SettingsViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 내 세션 북마크 (즐겨찾기 기능)
        //TODO : DarkTheme on/off

        initObserve()
    }

    private fun initObserve() {
        viewModel.osslEvent.eventObserve(viewLifecycleOwner) {
            startActivity(Intent(requireContext(), OssLicensesMenuActivity::class.java))
            OssLicensesMenuActivity.setActivityTitle(getString(R.string.settings_open_source_libraries))
        }
    }
}