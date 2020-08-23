package com.droidknights.app2020.ui.schedule.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.ActionMenuView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.core.view.doOnLayout
import androidx.navigation.fragment.navArgs
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.EventObserver
import com.droidknights.app2020.databinding.SessionDetailFragmentBinding
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionDetailFragment : BaseFragment<SessionDetailViewModel, SessionDetailFragmentBinding>(
    R.layout.session_detail_fragment,
    SessionDetailViewModel::class.java
) {

    private val args: SessionDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSession(args.sessionId)

        //TODO : Speaker 표시

        initBottomAppBar()
        initObserve()
    }

    private fun initBottomAppBar() {
        setOnMenuItemClickListenerInBottomAppBar()
        changeMarginTopToZeroForActionMenuView()
    }

    private fun setOnMenuItemClickListenerInBottomAppBar() {
        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_qna -> {
                    viewModel.onClickQnALink()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }
    }

    private fun changeMarginTopToZeroForActionMenuView() {
        binding.bottomAppBar.doOnLayout {
            val actionMenuView = getActionMenuViewInBottomAppBar(binding.bottomAppBar)
            val margin = getMarginTopOfActionMenuView(
                binding.bottomAppBar.height,
                actionMenuView?.height ?: 0
            )
            if (margin != 0) {
                actionMenuView?.translationY =- margin.toFloat()
            }
        }
    }

    private fun getMarginTopOfActionMenuView(
        bottomAppBarHeight: Int,
        actionMenuViewHeight: Int
    ): Int {
        if (bottomAppBarHeight != 0 && actionMenuViewHeight != 0) {
            val appBarHalf = bottomAppBarHeight / 2
            val menuHalf = actionMenuViewHeight / 2
            return appBarHalf - menuHalf
        }
        return 0
    }

    private fun getActionMenuViewInBottomAppBar(bottomAppBar: BottomAppBar): ActionMenuView? {
        for (i in 0 until bottomAppBar.childCount) {
            val view: View = bottomAppBar.getChildAt(i)
            if (view is ActionMenuView) {
                return view
            }
        }
        return null
    }

    private fun initObserve() {
        viewModel.videoEvent.observe(viewLifecycleOwner, EventObserver(this::openBrowser))

        viewModel.qnaEvent.observe(viewLifecycleOwner, EventObserver(this::openBrowser))

        viewModel.toastEvent.observe(viewLifecycleOwner, EventObserver(this::toastMessage))
    }

    private fun openBrowser(url: String) {
        CustomTabsIntent.Builder()
            .build()
            .launchUrl(requireContext(), url.toUri())
    }

    private fun toastMessage(@StringRes messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }
}