package com.autumnsun.birtutamhaber.data.remote.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteData(
    @SerializedName("data")
    val data: Data = Data()
) : Serializable {
    data class Data(
        @SerializedName("haberler")
        val haberler: List<Haberler> = listOf(),
        @SerializedName("yazarlar")
        val yazarlar: List<Yazarlar> = listOf()
    ) : Serializable {
        data class Haberler(
            @SerializedName("deeplink")
            val deeplink: String = "",
            @SerializedName("description")
            val description: String = "",
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("news_image")
            val newsImage: String = "",
            @SerializedName("title")
            val title: String = "",
            @SerializedName("writer")
            val writer: String = ""
        ) : Serializable

        data class Yazarlar(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("writer")
            val writer: String = "",
            @SerializedName("writer_image")
            val writerImage: String = ""
        ) : Serializable
    }
}