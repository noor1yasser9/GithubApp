package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.adapters.ItemRepositoryAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import com.noor.yasser.ps.githubapp.model.UserModel
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.*
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import com.noor.yasser.ps.githubapp.viewmodels.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DetailsUserFragment : Fragment(), ItemRepositoryAdapter.OnListItemViewClickListener {
    @Inject
    lateinit var mViewModel: UserDetailsViewModel

    @Inject
    lateinit var mUViewModel: ProfileViewModel

    private val mBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        ItemRepositoryAdapter(this)
    }
    private val mBundle by lazy { Bundle() }


    private lateinit var userModel: UserModel
    private var loadingDialog: IndeterminateProgressDialog? = null
    private var bundle: Bundle? = null
    private var isLoading = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundle = arguments
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
                            isLoading = true
                        }
                        ResultResponse.Status.SUCCESS -> {
                            userModel = it.data as UserModel
                            mBinding.data = userModel
                            mBundle.putString(USERNAME, userModel.login);
                            dismiss()
                            isLoading = false
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

        mBinding.btnGoToWeb.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(URL_DATA, userModel.htmlUrl)
            findNavController().navigate(R.id.openWebView, mBundle)
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
        mBinding.btnLink.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(URL_DATA, userModel.blog)
            findNavController().navigate(R.id.openWebView, mBundle)
        }
    }

    override fun onClickItem(item: RepositoryItem, type: Int) {
        val mBundle = Bundle()
        mBundle.putString(URL_DATA, item.htmlUrl)
        findNavController().navigate(R.id.openWebView, mBundle)
    }

    override fun onClickStart(item: RepositoryItem) {
        mUViewModel.getIfExists(item.id!!) {
            if (!it)
                mUViewModel.insertRepo(item)
            else
                mUViewModel.deleteRepo(item.id!!)
        }
    }

    override fun onChangeColorInserted(imageView: ImageView, item: RepositoryItem) {
        item.id?.let {
            mUViewModel.getIfExists(it) {
                if (it)
                    imageView.setImageResource(R.mipmap.ic_star_yellow_light)
                else
                    imageView.setImageResource(R.mipmap.ic_star_gray)
            }
        }
    }

    override fun onDestroy() {
        bundle?.let {
            it.getString(USERNAME)?.let {
                mViewModel.detailUser(it) {}
                mUViewModel.userFollowers(it)
                mUViewModel.userFollowing(it)
            }
        }
        super.onDestroy()
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