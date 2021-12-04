package com.autumnsun.birtutamhaber.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.FragmentDetailBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment
import com.autumnsun.birtutamhaber.ui.home.HomeFragment


class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailFragmentViewModel by viewModels()
    //TODO If you are using safe args open this
    //private val safeArgs: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val id = arguments?.getString(HomeFragment.NEWS_ID)

        Log.d("gettingId", id.toString())
        //TODO If you are using safe args open this
        /* safeArgs.let {
             viewModel.setDetailNewsWithArgs(it.detailNews)
         }*/
        id?.let { viewModel.getIdNews(it.toInt() - 1) }

        val detailController = DetailEpoxyController(requireActivity())
        binding.epoxyRecyclerView.setController(detailController)
        //TODO If you are using safe args open this
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