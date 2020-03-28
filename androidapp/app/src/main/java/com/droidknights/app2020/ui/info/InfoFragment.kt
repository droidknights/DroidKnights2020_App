package com.droidknights.app2020.ui.info

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.InfoFragmentBinding

/**
 * Created by jiyoung on 04/12/2019
 */
class InfoFragment : BaseFragment<InfoViewModel, InfoFragmentBinding>(
        R.layout.info_fragment,
        InfoViewModel::class
    ) {
    //TODO : 행사와 관련된 정보
    //TODO : 코엑스 위치 지도
    //TODO : 세션장 지도 이미지
    private val sponsorAdapter = SponsorAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        sponsorAdapter.run {
            submitList(viewModel.sponsorList)
            itemClickListener = object : DataBindingAdapter.ItemClickListener {
                override fun onClickItem(sessionId: String) {
                    val action = InfoFragmentDirections.actionInfoSponsor(sessionId)
                    findNavController().navigate(action)
                }
            }
        }

        rvSponsor.run {
            adapter = sponsorAdapter
        }
    }
}