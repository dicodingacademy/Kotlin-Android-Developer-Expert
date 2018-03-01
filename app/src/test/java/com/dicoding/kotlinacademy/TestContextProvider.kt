package com.dicoding.kotlinacademy

import com.dicoding.kotlinacademy.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by root on 3/1/18.
 */
class TestContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext = Unconfined
}