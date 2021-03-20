package com.jmarkstar.gumtree_challenge.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.InvalidClassException

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    lateinit var binding: Binding

    abstract fun layoutId(): Int

    var baseActivity: BaseActivity<*>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is BaseActivity<*>) {
            throw InvalidClassException("Fragment must be attached on BaseActivity")
        }
        baseActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return binding.root
    }

    inline fun <reified T : ViewModel> obtainSharedViewModel(): T =
        ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(T::class.java)
}
