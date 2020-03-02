package dev.sbeach.pictureprocurer.data.model.flickr

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("farm")
    val farm: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url_t")
    val thumbnail: String,
    @SerializedName("url_l")
    val large: String,
    @SerializedName("url_o")
    val original: String? = ""
)
