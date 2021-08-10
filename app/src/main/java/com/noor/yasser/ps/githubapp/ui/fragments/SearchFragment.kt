package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentSearchBinding
import com.noor.yasser.ps.githubapp.model.FollowersItem
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.model.search.SearchRequest
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.MemberItemDecoration
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import com.noor.yasser.ps.githubapp.utils.USERNAME
import com.noor.yasser.ps.githubapp.viewmodels.SearchViewModel
import com.noor.yasser.ps.githubapp.viewmodels.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), GenericAdapter.OnListItemViewClickListener<FollowersItem> {
    @Inject
    lateinit var mUserViewModel: UserDetailsViewModel
    @Inject
    lateinit var mViewModel: SearchViewModel
    private var loadingDialog: IndeterminateProgressDialog? = null
    private val mBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }
    private val mAdapter by lazy {
        GenericAdapter(R.layout.item_users, BR.data, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcData()
        mBinding.imageButtonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        mBinding.imageButtonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        mBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
//                    viewModel.getData().clear()
                    mViewModel.searchUser(it)
//                    search = it
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
//                    viewModel.getData().clear()
//                    movieAdapter.data = viewModel.getData()
                }
                return true
            }

        })


        lifecycleScope.launchWhenStarted {
            mViewModel.getUserSearchStateFlow().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                            Log.e("ttttttttttt", "Loading")
                            loadingDialog = getInstance();
                            if (!loadingDialog!!.isAdded)
                                loadingDialog!!.show(requireActivity().supportFragmentManager, "a")

                        }
                        ResultResponse.Status.SUCCESS -> {
                            val data = it.data as SearchRequest
                            mAdapter.data = data.items
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

    private fun rcData() {
        with(mBinding) {
            this.rcData.apply {
                adapter = mAdapter
                addItemDecoration(MemberItemDecoration())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            sharedElementEnterTransition =
                TransitionInflater.from(it).inflateTransition(android.R.transition.move)
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

    override fun onClickItem(itemViewModel: FollowersItem, type: Int) {
        val data = Bundle()
        data.putString(USERNAME, itemViewModel.login)
        mUserViewModel.detailUser(username = itemViewModel.login) {
            lifecycleScope.launchWhenStarted {
                findNavController().navigate(R.id.action_to_detailUserFragment, data)
            }

        }
    }
}