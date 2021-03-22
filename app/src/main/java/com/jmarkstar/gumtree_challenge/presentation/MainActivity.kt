package com.jmarkstar.gumtree_challenge.presentation

import android.os.Bundle
import com.jmarkstar.gumtree_challenge.R
import com.jmarkstar.gumtree_challenge.databinding.ActivityMainBinding
import com.jmarkstar.gumtree_challenge.presentation.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun layoutId() = R.layout.activity_main

    override fun screenTitleId() = R.string.app_name

    override fun navHostFragment() = R.id.nav_host_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            setupToolbar(toolbar)
        }
    }
}
