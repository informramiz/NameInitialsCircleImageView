package com.github.ramiz.nameinitialscircleimageview.common.imagedownloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.RestrictTo
import android.widget.ImageView

/**
 * Created by ramiz on 1/29/18.
 * Defining it as an interface to add a layer between
 * actual image downloading library and my code
 * so that code can be easily switched between different
 * image downloaders.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
interface ImageDownloader {
    fun downloadImage(context: Context, url: String, imageView: ImageView, placeHolder: Drawable)
    fun invalidateImage(context: Context, url: String);
}