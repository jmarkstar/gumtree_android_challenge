package com.jmarkstar.gumtree_challenge.presentation.common

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("hideOnLoading", requireAll = false)
fun View.hideOnLoading(resource: Resource<*>? = null) {
    visibility = if (resource is Resource.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading", requireAll = false)
fun ProgressBar.showOnLoading(resource: Resource<*>? = null) {
    visibility = if (resource is Resource.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError", requireAll = false)
fun TextView.showError(resource: Resource<*>? = null) {
    visibility = if (resource is Resource.Error) {
        text = resource.message
        View.VISIBLE
    } else
        View.GONE
}
