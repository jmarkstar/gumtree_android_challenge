package com.jmarkstar.gumtree_challenge.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class TestCoroutineRule : TestWatcher() {

    val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    fun runBlockingTest(block: suspend (TestCoroutineScope) -> Unit){
        testCoroutineDispatcher.runBlockingTest {
            block.invoke(this)
        }
    }

    fun resumeDispatcher() = testCoroutineDispatcher.resumeDispatcher()

    fun pauseDispatcher() = testCoroutineDispatcher.pauseDispatcher()

    fun advanceUntilIdle() = testCoroutineDispatcher.advanceUntilIdle()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
