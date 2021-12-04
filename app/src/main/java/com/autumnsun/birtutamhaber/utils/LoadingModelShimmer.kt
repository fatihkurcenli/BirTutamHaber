package com.autumnsun.birtutamhaber.utils

import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.ModelLoadingShimmerBinding

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class LoadingEpoxyModel(val shimmerStartStop: Boolean) :
    ViewBindingKotlinModel<ModelLoadingShimmerBinding>(R.layout.model_loading_shimmer) {

    override fun ModelLoadingShimmerBinding.bind() {
        if (shimmerStartStop) {
            shimmer.startShimmer()
        } else {
            shimmer.stopShimmer()
        }
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}