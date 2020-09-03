package com.droidknights.app2020.ui.schedule.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.EventObserver
import com.droidknights.app2020.databinding.SessionDetailFragmentBinding
import com.droidknights.app2020.util.startOpenUrl
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

        initMenu()
        initObserve()
    }

    private fun initMenu() {
        binding.toolbar.run {
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_alarm -> {
                        viewModel.onClickAlarm()
                        true
                    }
                    else -> false
                }
            }
        }
        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_share -> {
                    // TODO: Click Share
                    return@setOnMenuItemClickListener true
                }
                R.id.menu_qna -> {
                    viewModel.onClickQnALink()
                    return@setOnMenuItemClickListener true
                }
                R.id.menu_calendar -> {
                    // TODO: Click Calendar
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }
    }

    private fun initObserve() {
        viewModel.sessionContents.observe(viewLifecycleOwner) {
            val menu = binding.bottomAppBar.menu
            menu.findItem(R.id.menu_share).isVisible = !it.videoLink.isNullOrEmpty()
            menu.findItem(R.id.menu_qna).isVisible = !it.qnaLink.isNullOrEmpty()

            val detailAdapter = SessionDetailAdapter(viewModel, it)
            binding.sessionDetailRecyclerView.adapter = detailAdapter
        }

        viewModel.videoEvent.observe(viewLifecycleOwner, EventObserver(this::openBrowser))

        viewModel.qnaEvent.observe(viewLifecycleOwner, EventObserver(this::openBrowser))

        viewModel.toastEvent.observe(viewLifecycleOwner, EventObserver(this::toastMessage))
    }

    private fun openBrowser(url: String) {
        requireContext().startOpenUrl(url)
    }

    private fun toastMessage(@StringRes messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }
}