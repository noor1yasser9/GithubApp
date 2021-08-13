package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.ItemRepositoryAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentHomeBinding
import com.noor.yasser.ps.githubapp.model.repo.RepositoryItem
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.utils.MemberItemDecoration
import com.noor.yasser.ps.githubapp.utils.ResultResponse
import com.noor.yasser.ps.githubapp.utils.URL_DATA
import com.noor.yasser.ps.githubapp.viewmodels.HomeViewModel
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemRepositoryAdapter.OnListItemViewClickListener {

    private val mBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var mViewModel: HomeViewModel

    private val mAdapter by lazy {
        ItemRepositoryAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        mBinding.rcData.apply {
            adapter = mAdapter
            addItemDecoration(MemberItemDecoration())
        }

        mBinding.mSwipeRefreshLayout.setOnRefreshListener {
            mViewModel.getAllRepo()
        }
        lifecycleScope.launchWhenStarted {
            mViewModel.getRepoAllLiveData().collect {
                withContext(Dispatchers.Main) {
                    when (it.status) {
                        ResultResponse.Status.LOADING -> {
                        }
                        ResultResponse.Status.SUCCESS -> {
                            mAdapter.data = it.data as List<RepositoryItem>
                            mBinding.mSwipeRefreshLayout.isRefreshing = false
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

    private fun setupClickListeners() {
        mBinding.imageButtonSearch.setOnClickListener {
            val extras =
                FragmentNavigatorExtras(mBinding.cardViewToolbarContent to mBinding.cardViewToolbarContent.transitionName)
            findNavController().navigate(
                R.id.action_homeFragment_to_searchFragment,
                null,
                null,
                extras
            )
        }
    }


    override fun onClickItem(item: RepositoryItem, type: Int) {
        val mBundle = Bundle()
        mBundle.putString(URL_DATA, item.htmlUrl)
        findNavController().navigate(R.id.openWebView, mBundle)
    }

    override fun onClickStart(item: RepositoryItem) {
        mViewModel.deleteRepo(item.id!!)

    }

    override fun onChangeColorInserted(imageView: ImageView, item: RepositoryItem) {
        imageView.setImageResource(R.mipmap.ic_star_yellow_light)
    }
}