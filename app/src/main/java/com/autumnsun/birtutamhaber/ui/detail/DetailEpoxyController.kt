package com.autumnsun.birtutamhaber.ui.detail

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.ModelDescriptionNewsBinding
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

        DetailModel(context, detailNews).id("detail_scren").addTo(this)
        DetailDescription(detailNews).id("description_text_view").addTo(this)
    }

    data class DetailModel(
        val context: Context,
        val detailNews: RemoteData.Data.Haberler,
    ) : ViewBindingKotlinModel<ModelDetailBinding>(R.layout.model_detail) {
        override fun ModelDetailBinding.bind() {
            descriptionTitle.text = detailNews.title
            writerName.text = detailNews.writer
            detailNews.newsImage.let {
                Glide.with(context).load(detailNews.newsImage).into(detailImage)
            }
        }
    }

    data class DetailDescription(
        val detailNews: RemoteData.Data.Haberler,
    ) : ViewBindingKotlinModel<ModelDescriptionNewsBinding>(R.layout.model_description_news) {
        override fun ModelDescriptionNewsBinding.bind() {
            fullDescription.text = detailNews.description
        }
    }
}