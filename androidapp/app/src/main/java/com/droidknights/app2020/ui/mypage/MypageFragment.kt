package com.droidknights.app2020.ui.mypage


import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.MypageFragmentBinding

class MypageFragment :
    BaseFragment<MypageViewModel, MypageFragmentBinding>(
        R.layout.mypage_fragment,
        MypageViewModel::class
    ) {
    //TODO : 내 세션 북마크 (즐겨찾기 기능)
    //TODO : DarkTheme on/off
    //TODO : license 페이지
}