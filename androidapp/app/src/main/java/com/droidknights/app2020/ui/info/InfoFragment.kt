package com.droidknights.app2020.ui.info

import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.InfoFragmentBinding

/**
 * Created by jiyoung on 04/12/2019
 */
class InfoFragment :
    BaseFragment<InfoViewModel, InfoFragmentBinding>(
        R.layout.info_fragment,
        InfoViewModel::class
    ) {
    //TODO : 행사와 관련된 정보
    //TODO : 코엑스 위치 지도
    //TODO : 세션장 지도 이미지
}