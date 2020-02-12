package com.droidknights.app2020.ui.sponsor

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.R
import com.droidknights.app2020.ui.data.SponsorData
import javax.inject.Inject

class SponsorViewModel @Inject constructor() : ViewModel() {
    val sponsorList = listOf(
        SponsorData("toss", "https://toss.im/", R.drawable.ic_sponsor_toss),
        SponsorData("헤이딜러", "https://dealer.heydealer.com/", R.drawable.ic_sponsor_heydealer),
        SponsorData("LINE", "https://linepluscorp.com/", R.drawable.ic_sponsor_line),
        SponsorData("Remember", "https://rememberapp.co.kr/home", R.drawable.ic_sponsor_remember),
        SponsorData("강남언니", "https://about.gangnamunni.com/", R.drawable.ic_sponsor_gangnamunni),
        SponsorData("NAVER", "https://www.navercorp.com/", R.drawable.ic_sponsor_naver),
        SponsorData("my real trip", "https://www.myrealtrip.com/", R.drawable.ic_sponsor_myrealtrip),
        SponsorData("카카오페이", "https://www.kakaopay.com/", R.drawable.ic_sponsor_kakaopay),
        SponsorData("vcnc", "https://tadacareer.vcnc.co.kr/", R.drawable.ic_sponsor_vcnc)
    )
}