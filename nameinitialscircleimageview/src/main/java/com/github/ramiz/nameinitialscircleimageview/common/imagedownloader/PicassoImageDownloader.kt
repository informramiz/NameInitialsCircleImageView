package com.github.ramiz.nameinitialscircleimageview.common.imagedownloader

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by ramiz on 1/29/18.
 */
final class PicassoImageDownloader : ImageDownloader {

    override fun downloadImage(context: Context, url: String, imageView: ImageView) {
        Picasso
                .with(context)
                .load(url)
                .error(android.R.drawable.ic_delete)
                .into(imageView)
    }

}