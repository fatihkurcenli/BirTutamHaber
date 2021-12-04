package com.autumnsun.birtutamhaber.ui.writers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.FragmentWritersBinding
import com.autumnsun.birtutamhaber.ui.BaseFragment


class WritersFragment : BaseFragment(R.layout.fragment_writers) {
    private var _binding: FragmentWritersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WriterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWritersBinding.bind(view)
        val writerEpoxyController = WritersEpoxyController(requireActivity())
        binding.epoxyRecyclerView.setController(writerEpoxyController)
        writerEpoxyController.isLoading = true
        viewModel.writers.observe(viewLifecycleOwner) {
            writerEpoxyController.writerList = it as ArrayList<RemoteData.Data.Yazarlar>
        }
        viewModel.getWriters()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}