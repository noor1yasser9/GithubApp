package com.noor.yasser.ps.githubapp.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.noor.yasser.ps.githubapp.R
import com.noor.yasser.ps.githubapp.databinding.FragmentLoginBinding
import com.noor.yasser.ps.githubapp.ui.dialogs.IndeterminateProgressDialog
import com.noor.yasser.ps.githubapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var mViewModel: ProfileViewModel

    private val mBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
    private var loadingDialog: IndeterminateProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.mBtnSignIn.setOnClickListener {
            val username = mBinding.txtUsername.text.toString().trim();
            if (TextUtils.isEmpty(username)) {
                mBinding.txtUsername.error = getString(R.string.filed_is_required)
                mBinding.txtUsername.requestFocus()
            } else {
                loadingDialog = getInstance();
                if (!loadingDialog!!.isAdded)
                    loadingDialog!!.show(requireActivity().supportFragmentManager, "a")
                mViewModel.detailUser(username) {
                    if (it)
                        lifecycleScope.launchWhenStarted {
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                    else
                        Snackbar.make(
                            mBinding.tvUsername,
                            getString(R.string.not_found),
                            Snackbar.LENGTH_LONG
                        ).show()
                    loadingDialog!!.dismiss()
                    loadingDialog = null
                }
            }
        }

    }

    private fun getInstance(): IndeterminateProgressDialog {
        if (loadingDialog == null)
            loadingDialog = IndeterminateProgressDialog()
        return loadingDialog!!;
    }

}