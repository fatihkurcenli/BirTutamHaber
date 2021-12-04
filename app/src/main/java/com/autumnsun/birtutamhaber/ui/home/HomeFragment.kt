package com.autumnsun.birtutamhaber.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.FragmentHomeBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val epoxyController = HomeEpoxyController(requireActivity()) { safeArgSendDataDetail ->
            //TODO If you are using safe args open this
            /*        val navDirectionAction =
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(safeArgSendDataDetail)
                    navController.navigate(navDirectionAction)*/
            //TODO If you want deep link send use this commands!!
            /*    val args = Bundle()
                args.putSerializable(NEWS_ID, 3)
                NavDeepLinkBuilder(requireActivity())
                    .setGraph(R.navigation.navigation)
                    .setDestination(R.id.detailFragment)
                    .setArguments(args)
                    .setComponentName(MainActivity::class.java)
                    .createPendingIntent()
                    .send()*/
            val args = Bundle()
            args.putString(NEWS_ID, safeArgSendDataDetail.id.toString())
            NavDeepLinkBuilder(requireContext())
                .setGraph(R.navigation.navigation)
                .setDestination(R.id.detailFragment)
                .setArguments(args)
                .createPendingIntent()
                .send()
        }
        binding.epoxyRecyclerView.setController(epoxyController)
        epoxyController.isLoading = true
        viewModel.randomTabuData.observe(viewLifecycleOwner) { newsData ->
            epoxyController.newsList = newsData as ArrayList<RemoteData.Data.Haberler>
        }

        lifecycleScope.launch(Dispatchers.Main) {
            delay(3000L)
            if (!mainActivity.seeAppRate) {
                epoxyController.seeAppRate = true
                mainActivity.seeAppRate = true
            }
        }

        viewModel.getNewsData()
    }

    companion object {
        const val NEWS_ID = "id"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}