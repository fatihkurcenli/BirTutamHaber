package com.autumnsun.birtutamhaber.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

/*
 Created by Fatih Kurcenli on 12/3/2021
*/

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }

/*    protected val activityViewModel: MainViewModel
        get() = (activity as MainActivity).viewModel*/

    protected val mainActivity: MainActivity
        get() = (activity as MainActivity)
}