package com.noor.yasser.ps.githubapp.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.noor.yasser.ps.githubapp.R

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
    @JvmStatic
    @BindingAdapter("setColor")
    fun setColor(imageView: ImageView, lan: String?) {
        imageView.visibility = if (lan == null) View.GONE else View.VISIBLE

        if (lan.isNullOrBlank()) return
        imageView.setImageDrawable(getLanguageColor(imageView, lan))
    }

    @JvmStatic
    private fun getLanguageColor(view: ImageView, language: String?): Drawable {
        if (language == null) return ColorDrawable(Color.TRANSPARENT)

        val colorProvider: (Int) -> Drawable = { resId ->
            ColorDrawable(ContextCompat.getColor(view.context, resId))
        }

        return colorProvider(
            when (language) {
                "Kotlin" -> R.color.color_language_kotlin
                "Java" -> R.color.color_language_java
                "JavaScript" -> R.color.color_language_js
                "Python" -> R.color.color_language_python
                "HTML" -> R.color.color_language_html
                "CSS" -> R.color.color_language_css
                "Shell" -> R.color.color_language_shell
                "C++" -> R.color.color_language_cplus
                "C" -> R.color.color_language_c
                "Dart" -> R.color.color_language_Dart
                "ruby" -> R.color.color_language_ruby
                else -> R.color.color_language_other
            }
        )
    }
}