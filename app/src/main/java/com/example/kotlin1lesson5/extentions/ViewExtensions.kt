package com.example.kotlin1lesson5.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(image: String) {
    Glide.with(this).load(image).into(this)
}
