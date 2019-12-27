package com.droidknights.app2020.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.common.EventObserver
import com.droidknights.app2020.databinding.ScheduleFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.schedule_fragment.*
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class ScheduleFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ScheduleViewModel

    private lateinit var binding: ScheduleFragmentBinding
    private val scheduleAdapter = ScheduleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScheduleFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(ScheduleViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSchedule.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = scheduleAdapter
        }
        viewModel.sessionListData.observe(this, EventObserver {
            it.let(scheduleAdapter::submitList)
        })
    }
}