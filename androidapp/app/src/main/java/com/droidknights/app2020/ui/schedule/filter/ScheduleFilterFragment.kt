package com.droidknights.app2020.ui.schedule.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.data.Tag
import com.droidknights.app2020.databinding.ScheduleFilterFragmentBinding
import com.droidknights.app2020.ui.schedule.ScheduleViewModel
import com.google.android.material.chip.Chip

class ScheduleFilterFragment : BaseFragment<ScheduleViewModel, ScheduleFilterFragmentBinding>(
    R.layout.schedule_filter_fragment,
    ScheduleViewModel::class.java
) {
    private val scheduleViewModel by viewModels<ScheduleViewModel>()

    private var _allTags = listOf<Tag>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능

        _allTags = scheduleViewModel.allTags

        initView()
    }

    private fun initView() {
        val filterChipGroup = binding.filterChipGroup

        _allTags.forEach { tag ->
            val chip = LayoutInflater.from(activity).inflate(R.layout.filter_chip, null) as Chip
            chip.text = tag.name
            chip.isChecked = tag.isSelected
            chip.setOnClickListener { tag.isSelected = chip.isChecked }
            filterChipGroup.addView(chip)
        }

        binding.fabSubmit.setOnClickListener {
            scheduleViewModel.allTags = _allTags

            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
    }
}