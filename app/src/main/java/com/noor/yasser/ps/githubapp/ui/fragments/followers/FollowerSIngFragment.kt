package com.noor.yasser.ps.githubapp.ui.fragments.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.ViewPagerAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentFollowerSIngBinding
import com.noor.yasser.ps.githubapp.utils.POSITION_FOLLOWRES
import com.noor.yasser.ps.githubapp.utils.USERNAME
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FollowerSIngFragment : Fragment(R.layout.fragment_follower_s_ing) {

    private lateinit var mBinding: FragmentFollowerSIngBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentFollowerSIngBinding.bind(requireView())
        initViewPage()

    }

    @Inject
    lateinit var mViewModel: ProfileViewModel


    private fun initViewPage() {
        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        val followers = FollowersFragment()
        followers .arguments =  requireArguments()
        viewPagerAdapter.addFragment(followers, "Followers")
        val following = FollowingFragment()
        following.arguments = requireArguments()
        viewPagerAdapter.addFragment(following, "Following")
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
        requireArguments().apply {
            mBinding.viewPager.setCurrentItem(this.getInt(POSITION_FOLLOWRES), true)
        }
    }

}