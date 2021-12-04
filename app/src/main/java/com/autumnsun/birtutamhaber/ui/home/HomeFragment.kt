package com.autumnsun.birtutamhaber.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.FragmentHomeBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        /*     binding.fragmentSend.setOnClickListener {
                 mainActivity.navController.navigate(R.id.action_homeFragment_to_detailFragment)
             }*/

/*        val epoxyController = HomeEpoxyController { attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }*/
        val epoxyController = HomeEpoxyController(requireActivity())

        binding.epoxyRecyclerView.setController(epoxyController)
//        binding.epoxyRecyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))

        // Observing changes to the underlying list of data
        epoxyController.isLoading = true
        viewModel.randomTabuData.observe(viewLifecycleOwner) { newsData ->
            epoxyController.newsList = newsData as ArrayList<RemoteData.Data.Haberler>
        }
        viewModel.getNewsData()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}