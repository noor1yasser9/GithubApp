package com.noor.yasser.ps.githubapp.ui.fragments.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noor.yasser.ps.githubapp.BR
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.adapters.GenericAdapter
import com.noor.yasser.ps.githubapp.databinding.FragmentProfileBinding
import com.noor.yasser.ps.githubapp.databinding.FragmentRecyclerBinding

class FollowersFragment : Fragment() ,    GenericAdapter.OnListItemViewClickListener<Any> {

    private val mBinding by lazy {
        FragmentRecyclerBinding.inflate(layoutInflater)
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

    }

    override fun onClickItem(itemViewModel: Any, type: Int) {
        TODO("Not yet implemented")
    }
}