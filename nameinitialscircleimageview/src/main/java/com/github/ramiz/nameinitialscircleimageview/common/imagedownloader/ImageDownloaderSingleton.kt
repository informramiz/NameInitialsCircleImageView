package com.github.ramiz.nameinitialscircleimageview.common.imagedownloader

import android.content.Context
import android.widget.ImageView

/**
 * Created by ramiz on 1/29/18.
 * Singleton because we only need one ImageDownloader in whole application
 */

object ImageDownloaderSingleton {
    //this should be injected with Dagger2 but may
    //be for a later day

    //Do you see the benefit of ImageDownloader interface???
    //We can just plugin a different image downloader and other
    //code in app will not need a single line of code change. Cool na?
    private val imageDownloader: ImageDownloader = PicassoImageDownloader()

    fun loadImage(context: Context, url: String, imageView: ImageView) {
        imageDownloader.downloadImage(context, url, imageView)
    }
}
