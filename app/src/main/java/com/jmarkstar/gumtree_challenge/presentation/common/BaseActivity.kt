package com.jmarkstar.gumtree_challenge.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.jmarkstar.gumtree_challenge.R
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: Binding

    var navController: NavController? = null

    abstract fun layoutId(): Int

    open fun allowScreenshot() = false

    abstract fun navHostFragment(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // As OWASP suggestion and in case of this app, probably we don't want to allow the user to take screenshots of the app
        // or show the preview in the recent apps.
        if (allowScreenshot()) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }

        if (layoutId() != 0) {
            binding = DataBindingUtil.setContentView(this, layoutId())
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(
                if (navHostFragment() != 0) {
                    navHostFragment()
                } else {
                    R.id.nav_host_fragment
                }
            )

        navHostFragment?.apply {
            navController = (navHostFragment as NavHostFragment).navController
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    protected fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        navController?.apply {
            NavigationUI.setupWithNavController(toolbar, this)
        }
    }

    override fun onBackPressed() {
        // To support reverse transitions when user clicks the device back button
        supportFinishAfterTransition()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
