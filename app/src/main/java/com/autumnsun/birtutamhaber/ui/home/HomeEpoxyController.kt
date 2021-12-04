package com.autumnsun.birtutamhaber.ui.home

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.ModelNewsBinding
import com.autumnsun.birtutamhaber.utils.LoadingEpoxyModel
import com.autumnsun.birtutamhaber.utils.ViewBindingKotlinModel
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class HomeEpoxyController(
    val context: Context,
    private val onClickedCallback: (RemoteData.Data.Haberler) -> Unit
) : EpoxyController() {
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var newsList = ArrayList<RemoteData.Data.Haberler>()
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

        if (newsList.isEmpty()) {
            // todo show empty state
            return
        }

        /*       HeaderEpoxyModel("Recently Viewed").id("header_1").addTo(this)
               firstGroup.forEach { attraction ->
                   AttractionEpoxyModel(attraction, onClickedCallback)
                       .id(attraction.id)
                       .addTo(this)
               }*/


        newsList.forEachIndexed { index, news ->
            NewsModel(context, news, onClickedCallback).id(index).addTo(this)
        }

    }


    data class NewsModel(
        val context: Context,
        val news: RemoteData.Data.Haberler,
        val onClicked: (RemoteData.Data.Haberler) -> Unit
    ) : ViewBindingKotlinModel<ModelNewsBinding>(R.layout.model_news) {
        override fun ModelNewsBinding.bind() {
            description.text = news.title
            Glide.with(context).load(news.newsImage).into(newsImage)
            root.setOnClickListener {
                onClicked(news)
            }
        }
    }

}