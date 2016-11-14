package com.murakami.fankami.first.util

/**
 * Created by fankami on 西暦16/11/14.
 */

import android.support.annotation.IdRes
import android.view.View

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}