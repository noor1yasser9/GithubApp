package com.noor.yasser.ps.githubapp.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.noor.yasser.ps.githubapp.databinding.FragmentWebViewBinding
import com.noor.yasser.ps.githubapp.utils.URL_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BottomSheetDialogFragment() {
    private var loadingDialog: IndeterminateProgressDialog? = null
    private val mBinding by lazy {
        FragmentWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = mBinding.root

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = getInstance();
        if (!loadingDialog!!.isAdded)
            loadingDialog!!.show(requireActivity().supportFragmentManager, "a")
        arguments?.let {
            it.getString(URL_DATA)?.let { url ->
                with(mBinding.webView) {
                    loadUrl(url)
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            dismissLoading()
                        }
                    }
                }
            }
        }
    }

    private fun getInstance(): IndeterminateProgressDialog {
        if (loadingDialog == null)
            loadingDialog = IndeterminateProgressDialog()
        return loadingDialog!!
    }

    private fun dismissLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    override fun dismiss() {
        super.dismiss()
    }
}