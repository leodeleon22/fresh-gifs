package com.leodeleon.freshgifs.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.leodeleon.domain.entities.Image


object BindingAdapters {

    @BindingAdapter(value = ["gifImage"])
    @JvmStatic
    fun loadGif(view: ImageView, image: Image){

        val ratio = image.width.toFloat() / image.height.toFloat()
        val newHeight = (view.measuredWidth * ratio).toInt()
        view.updateLayoutParams { height = newHeight }
        val options = RequestOptions().apply {
            diskCacheStrategy(DiskCacheStrategy.NONE)
        }
        Glide.with(view)
            .asGif()
            .load(image.url)
            .apply(options)
            .into(view)

    }

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String){

        val options = RequestOptions().apply {
            diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        }

        Glide.with(view)
            .load(imageUrl)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    @BindingAdapter(value = ["selected"])
    @JvmStatic
    fun setSelected(view: View, selected: Boolean){
        view.isSelected = selected
    }

    @BindingAdapter(value = ["isVisible"])
    @JvmStatic
    fun setVisible(view: View, isVisible: Boolean){
        view.isVisible = isVisible
    }

}