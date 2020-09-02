package com.droidknights.app2020.ui.schedule.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
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
        initRecyclerView()
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
                        viewModel.onClickShare()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.sessionDetailRecyclerView.run {
            addItemDecoration(SessionDetailDecoration(this@SessionDetailFragment.requireContext()))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    binding.appbar.elevation = if (!canScrollVertically(-1)) {
                        0f
                    } else {
                        (4 * context.resources.displayMetrics.density + 0.5).toFloat()
                    }
                }
            })
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

        viewModel.shareEvent.observe(viewLifecycleOwner, EventObserver(this::shareVideoLink))
    }

    private fun openBrowser(url: String) {
        requireContext().startOpenUrl(url)
    }

    private fun toastMessage(@StringRes messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }

    private fun shareVideoLink(url: String) {
        startActivity(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        })
    }
}