package com.droidknights.app2020.ui.home

import android.os.Bundle
import android.view.View
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.FragmentHomeBinding
import com.droidknights.app2020.util.eventObserve
import com.droidknights.app2020.util.startOpenUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserve()
    }

    private fun initView() {
        binding.homeRecyclerView.addItemDecoration(HistoryItemDecoration())
    }

    private fun initObserve() {
        viewModel.openHomePageEvent.eventObserve(viewLifecycleOwner) { url ->
            requireContext().startOpenUrl(url)
        }
    }
}
