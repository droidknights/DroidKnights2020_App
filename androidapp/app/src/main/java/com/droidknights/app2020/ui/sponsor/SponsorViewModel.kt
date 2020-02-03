package com.droidknights.app2020.ui.sponsor

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.ui.data.SponsorData
import javax.inject.Inject

class SponsorViewModel @Inject constructor() : ViewModel() {
    val sponsorList = listOf<SponsorData>(
        SponsorData("toss", "https://toss.im/", null),
        SponsorData("헤이딜러", "https://dealer.heydealer.com/", null),
        SponsorData("LINE", "https://linepluscorp.com/", null),
        SponsorData("Remember", "https://rememberapp.co.kr/home", null),
        SponsorData("강남언니", "https://about.gangnamunni.com/", null),
        SponsorData("NAVER", "https://www.navercorp.com/", null),
        SponsorData("my real trip", "https://www.myrealtrip.com/", null),
        SponsorData("카카오페이", "https://www.kakaopay.com/", null),
        SponsorData("vcnc", "https://tadacareer.vcnc.co.kr/", null)
    )

}