package com.github.ramiz.nameinitialscircleimageview.common.imagedownloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by ramiz on 1/29/18.
 */
class PicassoImageDownloader : ImageDownloader {

    override fun downloadImage(context: Context, url: String, imageView: ImageView, placeHolder: Drawable) {
        Picasso
                .get()
                .load(url)
                .noFade()
                .placeholder(placeHolder)
                .into(imageView)
    }

}