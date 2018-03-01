package com.dicoding.kotlinacademy.util

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by root on 3/1/18.
 */
open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { UI }
}