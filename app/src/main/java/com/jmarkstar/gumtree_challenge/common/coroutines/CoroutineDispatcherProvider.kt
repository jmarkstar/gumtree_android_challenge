package com.jmarkstar.gumtree_challenge.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider : DispatcherProvider {

    override val Main: CoroutineDispatcher by lazy { Dispatchers.Main }
    override val IO: CoroutineDispatcher by lazy { Dispatchers.IO }
}
