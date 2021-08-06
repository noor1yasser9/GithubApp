package com.noor.yasser.ps.githubapp.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navHostFragment: Fragment? = null
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_host_home)

        val navController = navHostFragment!!.findNavController()

        NavigationUI.setupWithNavController(
            mBinding.bottomNavigation,
            navController
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment, R.id.profileFragment -> {
                    mBinding.bottomNavigation.isVisible = true
                }
                else -> {
                    mBinding.bottomNavigation.isVisible = false

                }
            }

        }

    }

    private fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_up)
            .setExitAnim(R.anim.slide_down)
            .setPopEnterAnim(R.anim.slide_up)
            .setPopExitAnim(R.anim.slide_down)
            .build()
    }
}