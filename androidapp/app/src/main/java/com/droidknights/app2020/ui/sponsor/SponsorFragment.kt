package com.droidknights.app2020.ui.sponsor

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.SponsorFragmentBinding

class SponsorFragment : BaseFragment<SponsorViewModel, SponsorFragmentBinding>(
    R.layout.sponsor_fragment,
    SponsorViewModel::class
) {
    //TODO : 후원사 리스트
    //TODO : app contributors 리스트

    private val sponsorAdapter = SponsorAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        sponsorAdapter.submitList(viewModel.sponsorList)
        binding.rvSponsor.apply {
            adapter = sponsorAdapter
        }
    }
}
