package com.autumnsun.birtutamhaber.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.FragmentDetailBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment
import com.autumnsun.birtutamhaber.ui.home.HomeFragment


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailFragmentViewModel by viewModels()

    //TODO If you are using safe args open this
    private val safeArgs: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        val id = arguments?.getString(HomeFragment.NEWS_ID)
        //TODO If you are using safe args open this
        safeArgs.let {
            viewModel.setDetailNewsWithArgs(it.detailNews)
        }
        //TODO If you are using intent open this
        //if id getting null getting default value on viewModel!!
        // id?.let { viewModel.getIdNews(it.toInt() - 1) } ?: viewModel.getIdNews()

        val detailController = DetailEpoxyController(requireActivity())
        binding.epoxyRecyclerView.setController(detailController)

        viewModel.detailNews.observe(viewLifecycleOwner) {
            detailController.detailNews = it
            mainActivity.supportActionBar?.let { actionBar ->
                actionBar.title = it.title
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}