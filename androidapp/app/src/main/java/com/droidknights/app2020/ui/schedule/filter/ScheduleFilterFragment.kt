package com.droidknights.app2020.ui.schedule.filter

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.data.Const
import com.droidknights.app2020.databinding.ScheduleFilterFragmentBinding
import com.droidknights.app2020.ui.schedule.ScheduleViewModel
import com.google.android.material.chip.Chip

class ScheduleFilterFragment : BaseFragment<ScheduleViewModel, ScheduleFilterFragmentBinding>(
    R.layout.schedule_filter_fragment,
    ScheduleViewModel::class
) {
    private lateinit var vm: ScheduleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO : 세션 태그 필터링 기능
        //TODO : 관심세션 북마크 기능

        vm = arguments!!.getSerializable(Const.SelectedTagsKey) as ScheduleViewModel

        initView()
    }

    private fun initView() {
        val filterChipGroup = binding.filterChipGroup

        filterChipGroup.children.forEach { view ->
            if (view is Chip) {
                view.isChecked = vm.selectedTags.contains(view.text)
            }
        }

        binding.fabSubmit.setOnClickListener {
            val selectedTags: ArrayList<String> = arrayListOf()

            for (i in 0..filterChipGroup.childCount) {
                val chip = filterChipGroup.getChildAt(i)

                if (chip is Chip) {
                    if (chip.isChecked) selectedTags.add(chip.text.toString())
                }
            }

            val intent = Intent(context, ScheduleFilterFragment::class.java)
            intent.putExtra(Const.SelectedTagsKey, selectedTags)

            val prevFragment = parentFragmentManager.fragments.first()
            prevFragment.onActivityResult(Const.FILTER_FRAGMENT_CODE, RESULT_OK, intent)
            parentFragmentManager.popBackStack()
        }
    }
}