package com.jmarkstar.gumtree_challenge.common

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jmarkstar.gumtree_challenge.HiltTestApplication_Application
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

// Robolectrict 4.x supports `androidx.test` and `AndroidJUnit4`.
@Config(sdk = [Build.VERSION_CODES.O_MR1], application = HiltTestApplication_Application::class)
@RunWith(AndroidJUnit4::class)
abstract class BaseTest
