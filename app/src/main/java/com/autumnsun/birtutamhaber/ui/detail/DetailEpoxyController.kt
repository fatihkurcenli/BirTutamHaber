package com.autumnsun.birtutamhaber.ui.detail

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.ModelDetailBinding
import com.autumnsun.birtutamhaber.utils.LoadingEpoxyModel
import com.autumnsun.birtutamhaber.utils.ViewBindingKotlinModel
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class DetailEpoxyController(val context: Context) : EpoxyController() {
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var detailNews = RemoteData.Data.Haberler()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel(isLoading).id("loading_state").addTo(this)
            return
        }

    /*    if (detailNews.) {
            // todo show empty state
            return
        }*/
        DetailModel(context, detailNews).id("detail_scren").addTo(this)
    }

    data class DetailModel(
        val context: Context,
        val detailNews: RemoteData.Data.Haberler,
    ) : ViewBindingKotlinModel<ModelDetailBinding>(R.layout.model_detail) {
        override fun ModelDetailBinding.bind() {
            descriptionTitle.text = detailNews.title
            fullDescription.text = detailNews.description
            detailNews.newsImage.let {
                Glide.with(context).load(detailNews.newsImage).into(detailImage)
            }
        }
    }
}