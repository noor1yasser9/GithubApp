package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.adapters.ViewPagerAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.model.UserModel
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.MemberItemDecoration
import com.noor.yasser.ps.githubapp.utils.POSITION_FOLLOWRES
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile),
    GenericAdapter.OnListItemViewClickListener<Any> {
    @Inject
    lateinit var mViewModel: ProfileViewModel

    private val mBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        GenericAdapter(R.layout.item_repos_repo, BR.data, this)
    }
    private val mBundle by lazy { Bundle() }


    private lateinit var userModel: UserModel
    private var loadingDialog: IndeterminateProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rcData.apply {
            adapter = mAdapter
            addItemDecoration(MemberItemDecoration())
        }

        mBinding.groupFollowers.setOnClickListener {
            mBundle.putInt(POSITION_FOLLOWRES, 1)
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment,mBundle)
        }
        mBinding.groupFollowing.setOnClickListener {
            mBundle.putInt(POSITION_FOLLOWRES, 0)
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment,mBundle)
        }

        lifecycleScope.launchWhenStarted {
            mViewModel.getUserDataStateFlow().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                        }
                        ResultResponse.Status.SUCCESS -> {
                            userModel = it.data as UserModel
                            mBinding.data = userModel
                        }
                        ResultResponse.Status.ERROR -> {
                        }
                        else -> {
                        }
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mViewModel.getUserRepoStateFlow().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                        }
                        ResultResponse.Status.SUCCESS -> {
                            mAdapter.data = it.data as List<RepositoryItem>
                        }
                        ResultResponse.Status.ERROR -> {
                        }
                        else -> {
                        }
                    }
                }
            }
        }

    }

    override fun onClickItem(itemViewModel: Any, type: Int) {
    }


}