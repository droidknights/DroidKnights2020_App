package com.droidknights.app2020.ui.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.data.Const
import com.droidknights.app2020.databinding.ScheduleFragmentBinding
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

    private lateinit var model: ScheduleViewModel

    private val scheduleAdapter = ScheduleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능 

        initView()
        initObserve()
    }

    override fun onResume() {
        super.onResume()

        viewModel.refresh()
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
            binding.floatingFilter.apply {
                visibility = View.VISIBLE
            }

            val selectedTag = viewModel.selectedTags
            val allTag = viewModel.allTags

            // 앱을 처음 켠 상태에서 초기화 해주는 경우
            if (selectedTag.isEmpty()) {
                val result = arrayListOf<String>()

                it.forEach { session ->
                    result.addAll(session.tag ?: emptyList())
                }

                val tags = result.distinctBy { s -> s }
                viewModel.selectedTags = tags

                if (allTag.isEmpty())
                    viewModel.allTags = tags
            }

            it.filter { session ->
                viewModel.selectedTags.intersect(session.tag!!).isNotEmpty()
            }.let(scheduleAdapter::submitList)
            Timber.d(TAG, "getSessionListData : $it")
        })

        viewModel.itemEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { sessionId ->
                val action = ScheduleFragmentDirections.actionScheduleToSessionDetail(sessionId)
                binding.root.findNavController().navigate(action)
            }
        })

        viewModel.fabEvent.observe(viewLifecycleOwner, Observer { event ->
            binding.floatingFilter.apply {
                visibility = View.GONE
            }

            val bundle = bundleOf(
                Pair(Const.SelectedTagsKey, viewModel.selectedTags),
                Pair(Const.AllTagsKey, viewModel.allTags)
            )
            val fragment = ScheduleFilterFragment()
            fragment.arguments = bundle

            setTargetFragment(targetFragment, Const.FILTER_FRAGMENT_CODE)

            parentFragmentManager.beginTransaction()
                .addToBackStack(fragment::class.java.simpleName)
                .add(R.id.frameLayout, fragment)
                .commit()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Const.FILTER_FRAGMENT_CODE && resultCode == Activity.RESULT_OK) {
            val selectedTags = data?.getStringArrayListExtra(Const.SelectedTagsKey)
            viewModel.selectedTags = selectedTags?.toList() ?: emptyList()
        }
    }
}