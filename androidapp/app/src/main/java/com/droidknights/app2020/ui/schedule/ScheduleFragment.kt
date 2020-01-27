package com.droidknights.app2020.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.ScheduleFragmentBinding
import com.droidknights.app2020.ext.assistedViewModels
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class ScheduleFragment : DaggerFragment() {
    private val TAG = this@ScheduleFragment::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val scheduleViewModel by assistedViewModels<ScheduleViewModel>{ viewModelFactory }

    private lateinit var binding: ScheduleFragmentBinding
    private val scheduleAdapter = ScheduleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScheduleFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserve()
    }

    private fun initView() {
        scheduleAdapter.apply {
            itemClickListener = object : DataBindingAdapter.ItemClickListener {
                override fun onClickItem(sessionId: String) {
                    scheduleViewModel.onClickItem(sessionId)
                }
            }
        }

        binding.rvSchedule.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = scheduleAdapter
        }
    }

    private fun initObserve() {
        scheduleViewModel.sessionListData.observe(viewLifecycleOwner, Observer {
            it.let(scheduleAdapter::submitList)
            Timber.d(TAG, "getSessionListData : $it")
            if(it.isNotEmpty()) binding.sessionsProgressBar.isVisible = false
        })

        scheduleViewModel.itemEvent.observe(viewLifecycleOwner, Observer {
            val bundle = bundleOf("sessionId" to it)
            binding.root.findNavController().navigate(R.id.sessionDetailFragment, bundle)
        })
    }
}