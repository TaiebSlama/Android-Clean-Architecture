package com.slama.cleanarchitecture.helper.utils

import android.os.Handler
import android.os.Looper

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

fun runOnMainThread(runnable: Runnable) {
    Handler(Looper.getMainLooper()).post(runnable)
}