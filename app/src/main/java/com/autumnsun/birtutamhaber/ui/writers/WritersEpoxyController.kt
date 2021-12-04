package com.autumnsun.birtutamhaber.ui.writers

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.databinding.ModelWritersBinding
import com.autumnsun.birtutamhaber.utils.LoadingEpoxyModel
import com.autumnsun.birtutamhaber.utils.ViewBindingKotlinModel
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class WritersEpoxyController(val context: Context) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var writerList = ArrayList<RemoteData.Data.Yazarlar>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }


    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel(isLoading).id("writer_loading_state").addTo(this)
            return
        }

        if (writerList.isEmpty()) {
            // todo show empty state
            return
        }

        writerList.forEachIndexed { index, writer ->
            WriterModel(context, writer).id(
                writer.id
            ).addTo(this)
        }
    }


    data class WriterModel(
        val context: Context,
        val news: RemoteData.Data.Yazarlar,
    ) : ViewBindingKotlinModel<ModelWritersBinding>(R.layout.model_writers) {
        override fun ModelWritersBinding.bind() {
            writerName.text = news.writer
            Glide.with(context).load(news.writerImage).circleCrop().into(writerImage)
        }
    }

}