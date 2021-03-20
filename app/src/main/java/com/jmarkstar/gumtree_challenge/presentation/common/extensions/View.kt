package com.jmarkstar.gumtree_challenge.presentation.common.extensions

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.GONE }

fun SwipeRefreshLayout.stopRefreshing() { this.isRefreshing = false }
