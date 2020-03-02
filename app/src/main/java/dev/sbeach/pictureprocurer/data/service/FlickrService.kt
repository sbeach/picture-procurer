package dev.sbeach.pictureprocurer.data.service

import dev.sbeach.pictureprocurer.BuildConfig
import dev.sbeach.pictureprocurer.data.remote.response.flickr.PhotosSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    // https://www.flickr.com/services/api/flickr.photos.search.html
    @GET("?method=flickr.photos.search" +
            "&api_key=${BuildConfig.FLICKR_KEY}" +
            /*
            safe_search (Optional)
            Safe search setting:
                1 for safe.
                2 for moderate.
                3 for restricted.
             */
            "&safe_search=1" +
            /*
            content_type (Optional)
            Content Type setting:
                1 for photos only.
                2 for screenshots only.
                3 for 'other' only.
                4 for photos and screenshots.
                5 for screenshots and 'other'.
                6 for photos and 'other'.
                7 for photos, screenshots, and 'other' (all).
             */
            "&content_type=1" +
            /*
            extras (Optional)
                A comma-delimited list of extra information to fetch for each returned record.
                Currently supported fields are:
                description, license, date_upload, date_taken, owner_name, icon_server,
                original_format, last_update, geo, tags, machine_tags, o_dims, views, media,
                path_alias, url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o

            https://www.flickr.com/services/api/misc.urls.html
            Size Suffixes
            The letter suffixes are as follows:
                s	small square 75x75
                q	large square 150x150
                t	thumbnail, 100 on longest side
                m	small, 240 on longest side
                n	small, 320 on longest side
                -	medium, 500 on longest side
                z	medium 640, 640 on longest side
                c	medium 800, 800 on longest side
                b	large, 1024 on longest side
                h	large 1600, 1600 on longest side
                k	large 2048, 2048 on longest side
                o	original image, either a jpg, gif or png, depending on source format

            extras to size map:
                url_t -> thumbnail ('t')
                url_l -> large ('b' vast majority of the time; 'h' or 'k' seem to be a possibility)
                url_o -> original ('o')
             */
            "&extras=url_t,url_l,url_o" +
            "&per_page=${ITEMS_PER_PAGE}" +
            "&sort=relevance" +
            "&format=json" +
            "&nojsoncallback=1"
    )
    fun photoSearch(@Query("text") text: String, @Query("page") page: Int): Call<PhotosSearch>

    companion object {
        const val BASE_URL = "https://www.flickr.com/services/rest/"
        private const val ITEMS_PER_PAGE = 25
    }
}
