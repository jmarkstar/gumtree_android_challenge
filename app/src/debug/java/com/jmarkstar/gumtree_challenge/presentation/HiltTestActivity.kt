package com.jmarkstar.gumtree_challenge.presentation

import androidx.databinding.ViewDataBinding
import com.jmarkstar.gumtree_challenge.presentation.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : BaseActivity<ViewDataBinding>() {

    override fun layoutId() = 0
    override fun screenTitleId() = 0
    override fun navHostFragment() = 0
}
