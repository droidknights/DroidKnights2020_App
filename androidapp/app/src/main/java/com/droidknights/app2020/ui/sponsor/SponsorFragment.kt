package com.droidknights.app2020.ui.sponsor

import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.droidknights.app2020.databinding.SponsorFragmentBinding

class SponsorFragment : BaseFragment<SponsorViewModel, SponsorFragmentBinding>(
    R.layout.sponsor_fragment,
    SponsorViewModel::class
)
