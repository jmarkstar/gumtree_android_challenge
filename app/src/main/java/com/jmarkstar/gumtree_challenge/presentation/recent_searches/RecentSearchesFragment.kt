package com.jmarkstar.gumtree_challenge.presentation.recent_searches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jmarkstar.gumtree_challenge.R
import com.jmarkstar.gumtree_challenge.databinding.FragmentRecentSearchesBinding
import com.jmarkstar.gumtree_challenge.presentation.common.BaseFragment

class RecentSearchesFragment : BaseFragment<FragmentRecentSearchesBinding>() {

    override fun layoutId() = R.layout.fragment_recent_searches

    override fun screenTitleId() = 0

    private val movieListViewModel: RecentSearchesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: will be implemented
    }
}
