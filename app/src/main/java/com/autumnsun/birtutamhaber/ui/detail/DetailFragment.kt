package com.autumnsun.birtutamhaber.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.FragmentDetailBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeArgs.let {
            viewModel.setDetailNewsWithArgs(it.detailNews)
        }
        
        val detailController = DetailEpoxyController(requireActivity())
        binding.epoxyRecyclerView.setController(detailController)
        viewModel.detailNews.observe(viewLifecycleOwner) {
            detailController.detailNews = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}