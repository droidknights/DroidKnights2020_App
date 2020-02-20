package com.droidknights.app2020.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.droidknights.app2020.R
import com.droidknights.app2020.databinding.MainActivityBinding
import com.droidknights.app2020.ext.assistedActivityViewModels
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by assistedActivityViewModels<MainActivityViewModel> { viewModelFactory }

    private var navHostFragment: NavHostFragment? = null

    private val binding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        navHostFragment?.let {
            NavigationUI.setupWithNavController(binding.bottomNav, it.navController)
        }
    }
}
