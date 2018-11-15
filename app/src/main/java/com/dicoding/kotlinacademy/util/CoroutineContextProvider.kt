package com.dicoding.kotlinacademy.util

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by root on 3/1/18.
 */
open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}