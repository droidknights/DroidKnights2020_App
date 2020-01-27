package com.droidknights.app2020.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.droidknights.app2020.databinding.MypageFragmentBinding
import com.droidknights.app2020.ext.assistedViewModels
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MypageFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mypageViewModel by assistedViewModels<MypageViewModel>{ viewModelFactory }

    private lateinit var binding: MypageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MypageFragmentBinding.inflate(inflater, container, false).apply { lifecycleOwner = viewLifecycleOwner }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
