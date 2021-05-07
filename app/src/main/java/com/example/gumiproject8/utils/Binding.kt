package com.example.gumiproject8.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.example.gumiproject8.R
import com.example.gumiproject8.model.Thumbnail

object Binding {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImageView(imageView: ImageView, url: String) {
        imageView.load(url){
            placeholder(R.drawable.ic_launcher_background)
            size(150, 200)
        }
    }
    @BindingAdapter("setVisibityShow")
    @JvmStatic
    fun setVisity(view: View, check: Boolean) {
        if(check) view.visible() else view.gone()
    }
}