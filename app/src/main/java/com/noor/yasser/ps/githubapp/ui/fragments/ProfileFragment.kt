package com.noor.yasser.ps.githubapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.adapters.ItemRepositoryAdapter
import com.noor.yasser.ps.githubapp.adapters.ViewPagerAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.model.UserModel
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.MemberItemDecoration
import com.noor.yasser.ps.githubapp.utils.POSITION_FOLLOWRES
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import com.noor.yasser.ps.githubapp.utils.USERNAME
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile),
    ItemRepositoryAdapter.OnListItemViewClickListener {
    @Inject
    lateinit var mViewModel: ProfileViewModel

    private val mBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        ItemRepositoryAdapter(this)
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
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment, mBundle)
        }
        mBinding.groupFollowing.setOnClickListener {
            mBundle.putInt(POSITION_FOLLOWRES, 0)
            findNavController().navigate(R.id.action_nav_profile_to_followerSIngFragment, mBundle)
        }

        lifecycleScope.launchWhenStarted {
            mViewModel.getUserDataStateFlow().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                            loadingDialog = getInstance();
                            if (!loadingDialog!!.isAdded)
                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")

                        }
                        ResultResponse.Status.SUCCESS -> {
                            userModel = it.data as UserModel
                            mBinding.data = userModel
                            mBundle.putString(USERNAME, userModel.login);
                            dismiss()
                        }
                        ResultResponse.Status.ERROR -> {
                            dismiss()
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
                            loadingDialog = getInstance();
                            if (!loadingDialog!!.isAdded)
                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")

                        }
                        ResultResponse.Status.SUCCESS -> {
                            mAdapter.data = it.data as List<RepositoryItem>
                            dismiss()
                        }
                        ResultResponse.Status.ERROR -> {
                            dismiss()
                        }
                        else -> {
                        }
                    }
                }
            }
        }

    }

    override fun onClickItem(itemViewModel: RepositoryItem, type: Int) {

    }

    override fun onClickStart(item: RepositoryItem) {
        mViewModel.insertRepo(item)

        lifecycleScope.launchWhenStarted {
            mViewModel.getRepoInsertLiveData().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                            loadingDialog = getInstance();
                            if (!loadingDialog!!.isAdded)
                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")

                        }
                        ResultResponse.Status.SUCCESS -> {
                            val data = it.data
                            Log.e("tttttttttttt", data.toString())
                            dismiss()
                        }
                        ResultResponse.Status.ERROR -> {
                            dismiss()
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    override fun onChangeColorInserted(imageView: ImageView, item: RepositoryItem) {
        item.id?.let {

            mViewModel.getIfExists(it)
        }


        lifecycleScope.launchWhenStarted {
            mViewModel.getRepoIsExistsLiveData().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
//                            loadingDialog = getInstance();
//                            if (!loadingDialog!!.isAdded)
//                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")

                        }
                        ResultResponse.Status.SUCCESS -> {
                            val data = it.data as Boolean
                            if (data)
                                imageView.setImageResource(R.mipmap.ic_star_yellow_light)
                            else
                                imageView.setImageResource(R.mipmap.ic_star_gray)
//                            dismiss()
                        }
                        ResultResponse.Status.ERROR -> {
//                            dismiss()
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    private fun getInstance(): IndeterminateProgressDialog {
        if (loadingDialog == null)
            loadingDialog = IndeterminateProgressDialog()
        return loadingDialog!!;
    }

    private fun dismiss() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

}