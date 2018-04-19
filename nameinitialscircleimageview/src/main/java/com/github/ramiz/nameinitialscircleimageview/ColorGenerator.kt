package com.github.ramiz.nameinitialscircleimageview

import android.support.annotation.ColorInt

interface ColorGenerator {
    @ColorInt
    fun generateColor(key: Any): Int
}