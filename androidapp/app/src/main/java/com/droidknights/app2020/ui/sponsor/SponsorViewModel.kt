package com.droidknights.app2020.ui.sponsor

import androidx.lifecycle.ViewModel
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.ui.model.asUiModel
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class SponsorViewModel @Inject constructor() : ViewModel() {

    val sponsorList = listOf(
        Sponsor("toss", "https://toss.im/", R.drawable.ic_sponsor_toss),
        Sponsor("헤이딜러", "https://dealer.heydealer.com/", R.drawable.ic_sponsor_heydealer),
        Sponsor("LINE", "https://linepluscorp.com/", R.drawable.ic_sponsor_line),
        Sponsor("Remember", "https://rememberapp.co.kr/home", R.drawable.ic_sponsor_remember),
        Sponsor("강남언니", "https://about.gangnamunni.com/", R.drawable.ic_sponsor_gangnamunni),
        Sponsor("NAVER", "https://www.navercorp.com/", R.drawable.ic_sponsor_naver),
        Sponsor("my real trip", "https://www.myrealtrip.com/", R.drawable.ic_sponsor_myrealtrip),
        Sponsor("카카오페이", "https://www.kakaopay.com/", R.drawable.ic_sponsor_kakaopay),
        Sponsor("vcnc", "https://tadacareer.vcnc.co.kr/", R.drawable.ic_sponsor_vcnc)
    ).map { sponsor -> sponsor.asUiModel() }
}