package com.droidknights.app2020.ui.sponsor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.droidknights.app2020.databinding.SponsorFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SponsorFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SponsorViewModel

    private lateinit var binding: SponsorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SponsorFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(SponsorViewModel::class.java)
    }
}
