package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.adapters.ViewPagerAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile),
    GenericAdapter.OnListItemViewClickListener<Any> {

    private val mBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        GenericAdapter(R.layout.item_repos_repo, BR._all, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rcData.adapter = mAdapter

        mBinding.groupFollowers.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment)
        }
        mBinding.groupFollowing.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment)
        }
    }

    override fun onClickItem(itemViewModel: Any, type: Int) {
        TODO("Not yet implemented")
    }


}