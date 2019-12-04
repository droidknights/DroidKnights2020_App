package com.droidknights.app2020.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.droidknights.app2020.R
import com.droidknights.app2020.common.EventObserver
import com.droidknights.app2020.databinding.HomeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by jiyoung on 29/11/2019
 */
class HomeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
        binding.vm = viewModel

        viewModel.navigateToSchedule.observe(this, EventObserver {
            findNavController().navigate(R.id.action_homeFragment_to_scheduleFragment)
        })

        viewModel.navigateToInfo.observe(this, EventObserver{
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        })

        binding.run {
            viewModel.homeText.observe(this@HomeFragment, Observer {
                tvHomePage.text = it
            })
        }
    }
}