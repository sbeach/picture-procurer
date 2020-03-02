package dev.sbeach.pictureprocurer.data.remote.response.flickr

import com.google.gson.annotations.SerializedName
import dev.sbeach.pictureprocurer.data.model.flickr.Photos

data class PhotosSearch(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String,
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = ""
)
