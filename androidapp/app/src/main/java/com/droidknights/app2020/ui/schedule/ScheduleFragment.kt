package com.droidknights.app2020.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.BR
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.ScheduleFragmentBinding
import com.droidknights.app2020.ext.assistedActivityViewModels
import com.droidknights.app2020.ui.schedule.filter.ScheduleFilterFragment
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

    val _viewModel: ScheduleViewModel by assistedActivityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능 

        with(binding) {
            setVariable(BR.vm, _viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        initView()
        initObserve()
    }

    override fun onResume() {
        super.onResume()

        _viewModel.refresh()
    }

    private fun initView() {
        scheduleAdapter.apply {
            itemClickListener = object : DataBindingAdapter.ItemClickListener {
                override fun onClickItem(sessionId: String) {
                    _viewModel.onClickItem(sessionId)
                }
            }
        }

        binding.rvSchedule.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = scheduleAdapter
        }
    }

    private fun initObserve() {
        _viewModel.sessionList.observe(viewLifecycleOwner, Observer {
            binding.floatingFilter.isVisible = true

            val allTag = _viewModel.allTags

            // 앱을 처음 켠 상태에서 초기화 해주는 경우
            if (allTag.isEmpty()) {
                _viewModel.allTags = sequence { it.map { s -> yieldAll(s.tag.orEmpty()) } }
                    .distinctBy { s -> s }
                    .toList()
            }

            it.filter { session ->
                _viewModel.selectedTags.intersect(session.tag.orEmpty()).isNotEmpty()
            }.let(scheduleAdapter::submitList)
            Timber.d(TAG, "getSessionListData : $it")
        })

        _viewModel.itemEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { sessionId ->
                val action = ScheduleFragmentDirections.actionScheduleToSessionDetail(sessionId)
                binding.root.findNavController().navigate(action)
            }
        })

        _viewModel.fabEvent.observe(viewLifecycleOwner, Observer { event ->
            binding.floatingFilter.isVisible = false

            val fragment = ScheduleFilterFragment()
            parentFragmentManager.beginTransaction()
                .addToBackStack(fragment::class.java.simpleName)
                .add(R.id.frameLayout, fragment)
                .commit()
        })
    }
}