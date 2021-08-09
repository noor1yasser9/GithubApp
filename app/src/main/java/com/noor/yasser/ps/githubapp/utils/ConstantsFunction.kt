package com.noor.yasser.ps.githubapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_search.*


fun View.hideKeyboard(activity: Activity) {
    val view = activity.view.rootView
    val inputManager: InputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}