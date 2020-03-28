package com.droidknights.app2020.ui.info.sponsor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.SponsorFragmentBinding

class SponsorFragment : BaseFragment<SponsorViewModel, SponsorFragmentBinding>(
    R.layout.sponsor_fragment,
    SponsorViewModel::class
) {
    private val args: SponsorFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() = with(binding) {
        webSponsor.run {
            settings.javaScriptEnabled = true
        }
    }

    private fun initData() = with(viewModel.trigger) {
        setupUrl(args.webUrl)
    }
}