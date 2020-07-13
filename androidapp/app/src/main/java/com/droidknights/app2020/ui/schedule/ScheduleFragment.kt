package com.droidknights.app2020.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.ScheduleFragmentBinding
import com.droidknights.app2020.ui.schedule.filter.ScheduleFilterFragment
import kotlinx.android.synthetic.main.schedule_fragment.*
import timber.log.Timber

/**
 * Created by jiyoung on 04/12/2019
 */
class ScheduleFragment : BaseFragment<ScheduleViewModel, ScheduleFragmentBinding>(
    R.layout.schedule_fragment,
    ScheduleViewModel::class
) {
    private val TAG = this@ScheduleFragment::class.java.simpleName

    private val scheduleAdapter = ScheduleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능 

        initView()
        initObserve()
    }

    private fun initView() {
        scheduleAdapter.apply {
            itemClickListener = object : DataBindingAdapter.ItemClickListener {
                override fun onClickItem(sessionId: String) {
                    viewModel.onClickItem(sessionId)
                }
            }
        }

        binding.rvSchedule.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = scheduleAdapter
        }
    }

    private fun initObserve() {
        viewModel.sessionList.observe(viewLifecycleOwner, Observer {
            it.let(scheduleAdapter::submitList)
            Timber.d(TAG, "getSessionListData : $it")
        })

        viewModel.itemEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { sessionId ->
                val action = ScheduleFragmentDirections.actionScheduleToSessionDetail(sessionId)
                binding.root.findNavController().navigate(action)
            }
        })

        viewModel.fabEvent.observe(viewLifecycleOwner, Observer { event ->
            parentFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, ScheduleFilterFragment())
                .commit()
        })
    }
}