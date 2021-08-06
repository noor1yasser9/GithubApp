package com.noor.yasser.ps.githubapp.ui.fragments.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.ViewPagerAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentFollowerSIngBinding

class FollowerSIngFragment : Fragment(R.layout.fragment_follower_s_ing) {

    private lateinit var mBinding: FragmentFollowerSIngBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentFollowerSIngBinding.bind(requireView())


        initViewPage()

    }


    private fun initViewPage() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPagerAdapter.addFragment(FollowersFragment(), "Followers")
        viewPagerAdapter.addFragment(FollowingFragment(), "Following")
        mBinding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(
            mBinding.tableLayout, mBinding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> {
                    tab.text = "Followers"
                }
                1 -> {
                    tab.text = "Following"
                }
            }
        }.attach()

    }

}