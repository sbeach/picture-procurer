package dev.sbeach.pictureprocurer.data.model.flickr

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("perpage")
    val perpage: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("photo")
    val photos: List<Photo>
)
