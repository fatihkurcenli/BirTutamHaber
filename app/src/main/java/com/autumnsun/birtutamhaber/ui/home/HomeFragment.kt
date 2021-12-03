package com.autumnsun.birtutamhaber.ui.home

import android.os.Bundle
import android.view.View
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.FragmentHomeBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment


class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.fragmentSend.setOnClickListener {
            mainActivity.navController.navigate(R.id.action_homeFragment_to_detailFragment)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}