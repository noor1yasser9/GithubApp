package com.noor.yasser.ps.githubapp.adapters

import android.app.FragmentManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.annotation.Nullable


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val lf = ArrayList<Fragment>()
    private val lt = ArrayList<String>()


    override fun getItemCount(): Int {
        return lf.size
    }

    override fun createFragment(position: Int): Fragment {
        return lf[position]
    }

    fun addFragment(fragment: Fragment?, title: String?) {
        lf.add(fragment!!)
        lt.add(title!!)
    }

}