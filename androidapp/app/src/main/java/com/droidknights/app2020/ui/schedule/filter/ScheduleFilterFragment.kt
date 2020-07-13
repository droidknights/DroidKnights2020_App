package com.droidknights.app2020.ui.schedule.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.databinding.ScheduleFilterFragmentBinding
import com.droidknights.app2020.ui.schedule.ScheduleFragmentDirections
import com.droidknights.app2020.ui.schedule.ScheduleViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.schedule_filter_fragment.*
import kotlinx.android.synthetic.main.schedule_fragment.*
import timber.log.Timber

class ScheduleFilterFragment : BaseFragment<ScheduleViewModel, ScheduleFilterFragmentBinding>(
    R.layout.schedule_filter_fragment,
    ScheduleViewModel::class
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능

        initView()
        initObserve()
    }

    private fun initView() {
    }

    private fun initObserve() {
        viewModel.submitEvent.observe(viewLifecycleOwner, Observer { event ->
            val selectedTags: ArrayList<String> = arrayListOf()

            for (i in 0..filterChipGroup.childCount) {
                val chip = filterChipGroup.getChildAt(i)

                if (chip is Chip) {
                    if (chip.isChecked) selectedTags.add(chip.text.toString())
                }
            }

            viewModel.selectedTags = selectedTags

            parentFragmentManager
                .beginTransaction()
                .remove(this)
                .commit()
        })
    }
}