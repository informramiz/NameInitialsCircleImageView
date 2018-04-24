package com.github.ramiz.nameinitialscircleimageview.common

import android.support.annotation.RestrictTo
import android.util.Log
import com.github.ramiz.nameinitialscircleimageview.BuildConfig

/**
 * Created by ramiz on 1/29/18.
 * this class is Singleton
 */

@RestrictTo(RestrictTo.Scope.LIBRARY)
object LogUtils {
    fun v(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.v(tag, message)
    }

    fun d(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.d(tag, message)
    }

    fun i(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.i(tag, message)
    }

    fun w(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.w(tag, message)
    }

    fun e(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.e(tag, message)
    }
}
