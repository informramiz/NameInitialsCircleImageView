package com.github.ramiz.nameinitialscircleimageview

import android.graphics.Color
import android.support.annotation.ColorInt
import java.util.*

class MaterialColorGenerator : ColorGenerator {
    @ColorInt
    private val DEFAULT_COLORS = Arrays.asList(
            "#D32F2F", //RED
            "#757575", //PINK
            "#7B1FA2", //Purple
            "#512DA8", //Deep Purple
            "#303F9F", //Indigo
            "#1976D2", //blue
            "#0288D1", //light blue
            "#00796B", //teal
            "#388E3C", //green
            "#F57C00", //orange
            "#E64A19", //deep orange
            "#5D4037", //brown
            "#9E9E9E", //grey
            "#607D8B" //blue grey
    )

    @ColorInt
    private val mColors: List<Int>

    constructor() {
        this.mColors = DEFAULT_COLORS.map { str -> Color.parseColor(str) }
    }

    constructor(@ColorInt colors: List<Int>) {
        this.mColors = colors;
    }

    override fun generateColor(key: Any): Int {
        return mColors[Math.abs(key.hashCode()) % mColors.size]
    }
}