package com.droidknights.app2020.ui.mypage


import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.MypageFragmentBinding

class MypageFragment :
    BaseFragment<MypageViewModel, MypageFragmentBinding>(
        R.layout.mypage_fragment,
        MypageViewModel::class
    )