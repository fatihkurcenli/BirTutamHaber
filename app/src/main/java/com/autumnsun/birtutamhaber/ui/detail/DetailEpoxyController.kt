package com.autumnsun.birtutamhaber.ui.detail

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.ModelDetailBinding
import com.autumnsun.birtutamhaber.utils.ViewBindingKotlinModel

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
        DetailModel(context, detailNews).id("detail_scren").addTo(this)
    }

    data class DetailModel(
        val context: Context,
        val detailNews: RemoteData.Data.Haberler,
    ) : ViewBindingKotlinModel<ModelDetailBinding>(R.layout.model_detail) {
        override fun ModelDetailBinding.bind() {
            /*description.text = news.title
            Glide.with(context).load(news.newsImage).into(newsImage)
            root.setOnClickListener {
                onClicked(news)
            }*/
        }
    }
}