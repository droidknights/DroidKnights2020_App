package com.droidknights.app2020.ui.schedule.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.EventObserver
import com.droidknights.app2020.databinding.SessionDetailFragmentBinding
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
                    R.id.menu_share -> {
                        // TODO: Click Share
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initObserve() {
        viewModel.sessionContents.observe(viewLifecycleOwner) {
            binding.sessionDetailVideoLinkTextView.isVisible = !it.videoLink.isNullOrEmpty()
            binding.sessionDetailQnALinkTextView.isVisible = !it.qnaLink.isNullOrEmpty()

            val detailAdapter = SessionDetailAdapter(viewModel, it)
            binding.sessionDetailRecyclerView.adapter = detailAdapter
        }

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