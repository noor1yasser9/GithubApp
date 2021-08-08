package com.noor.yasser.ps.githubapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object HolderAdapter {

    @JvmStatic
    @BindingAdapter("circleImageUrl")
    fun setCircleImageUrl(imageView: ImageView, url: String?) {
        if (url.isNullOrEmpty()) return
        Glide
            .with(imageView.context)
            .load(url)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}