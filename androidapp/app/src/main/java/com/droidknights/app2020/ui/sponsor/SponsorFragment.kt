package com.droidknights.app2020.ui.sponsor

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.SponsorFragmentBinding

/**
 * Created by jiyoung on 04/12/2019
 */
class SponsorFragment : BaseFragment<SponsorViewModel, SponsorFragmentBinding>(
    R.layout.sponsor_fragment,
    SponsorViewModel::class
) {
    //TODO : 행사와 관련된 정보
    //TODO : 코엑스 위치 지도
    //TODO : 세션장 지도 이미지
    private val sponsorAdapter =
        SponsorAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        sponsorAdapter.run {
            submitList(viewModel.sponsorList)
            itemClickListener = object : DataBindingAdapter.ItemClickListener {
                override fun onClickItem(sessionId: String) {
                    sessionId.let(::openWebUrl)
                }
            }
        }

        binding.rvSponsor.run {
            adapter = sponsorAdapter
        }
    }

    private fun openWebUrl(webUrl: String) {
        context?.let { context ->
            val uri = Uri.parse(webUrl)

            CustomTabsIntent.Builder()
                .setToolbarColor(context.getColor(R.color.colorPrimary))
                .build()
                .launchUrl(context, uri)
        }
    }
}