package com.jmarkstar.gumtree_challenge.common.espresso.assertions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.Matcher

/**
 * Assertion that check RecyclerView itemCount.
 * Example usage:
 * Firstly you need to import it,
 *
 * <pre><code>
 * import com.jmarkstar.princestheatre.common.esspreso.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
 * <pre><code>
 *
 * Then,
 *
 * <pre><code>
 * onView(withId(R.id.rvMovies)).check(withItemCount(`is`(moviesMock.size)))
 * <pre><code>
 */
class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) : ViewAssertion {

    companion object {

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        if (view !is RecyclerView) {
            throw IllegalStateException("The asserted view is not RecyclerView")
        }

        if (view.adapter == null) {
            throw IllegalStateException("No adapter is assigned to RecyclerView")
        }

        assertThat("RecyclerView item count", view.adapter!!.itemCount, matcher)
    }
}
