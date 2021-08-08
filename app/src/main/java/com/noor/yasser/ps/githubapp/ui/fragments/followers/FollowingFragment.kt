package com.noor.yasser.ps.githubapp.ui.fragments.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import com.noor.yasser.ps.githubapp.databinding.FragmentRecyclerBinding
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.MemberItemDecoration
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class FollowingFragment : Fragment(), GenericAdapter.OnListItemViewClickListener<Any> {
    @Inject
    lateinit var mViewModel: ProfileViewModel


    private val mBinding by lazy {
        FragmentRecyclerBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        GenericAdapter(R.layout.item_users, BR.data, this)
    }
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
        lifecycleScope.launchWhenStarted {
            mViewModel.getUserFollowingStateFlow().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                            loadingDialog = getInstance();
                            if (!loadingDialog!!.isAdded)
                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")
                        }
                        ResultResponse.Status.SUCCESS -> {
                            mAdapter.data = it.data as List<FollowersItem>
                            dismiss()
                        }
                        ResultResponse.Status.ERROR -> {
                            dismiss()
                        }
                        else -> {
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    override fun onClickItem(itemViewModel: Any, type: Int) {

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