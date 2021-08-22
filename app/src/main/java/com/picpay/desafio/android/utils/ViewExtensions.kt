package com.picpay.desafio.android.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.gone() {
    if(visibility != View.GONE) {
        this.animate().alpha(0.0f);
        this.visibility = View.GONE
    }
}

fun View.visible() {
    if(visibility != View.VISIBLE) {
        visibility = View.VISIBLE
        animate().alpha(1.0f).duration = 1000L;
    }
}

fun ViewGroup.inflate(layoutId : Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)


