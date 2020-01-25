package com.droidknights.app2020.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.droidknights.app2020.R
import com.droidknights.app2020.ext.assistedActivityViewModels
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    private val TAG = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by assistedActivityViewModels<MainActivityViewModel> { viewModelFactory }

    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        navHostFragment?.let {
            NavigationUI.setupWithNavController(bottomNav, it.navController)
        }
    }
}
