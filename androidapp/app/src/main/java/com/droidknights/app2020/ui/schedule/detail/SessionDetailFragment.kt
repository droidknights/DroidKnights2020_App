package com.droidknights.app2020.ui.schedule.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.SessionDetailFragmentBinding

class SessionDetailFragment : BaseFragment<SessionDetailViewModel, SessionDetailFragmentBinding>(
    R.layout.session_detail_fragment,
    SessionDetailViewModel::class
) {

    private val args: SessionDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSessionFromFirestore(args.sessionId)

        //TODO : Speaker 표시
    }
}