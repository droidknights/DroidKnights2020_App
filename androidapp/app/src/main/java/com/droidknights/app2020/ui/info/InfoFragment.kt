package com.droidknights.app2020.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.droidknights.app2020.databinding.InfoFragmentBinding
import com.droidknights.app2020.ext.assistedViewModels
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class InfoFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val infoViewModel by assistedViewModels<InfoViewModel>{ viewModelFactory }

    private lateinit var binding: InfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InfoFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}