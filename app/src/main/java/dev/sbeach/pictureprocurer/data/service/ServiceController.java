package dev.sbeach.pictureprocurer.data.service;

import androidx.annotation.VisibleForTesting;

import dev.sbeach.pictureprocurer.data.remote.response.flickr.PhotosSearch;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceController {

    private FlickrService flickrService;

    public ServiceController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FlickrService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flickrService = retrofit.create(FlickrService.class);
    }

    @VisibleForTesting
    ServiceController(FlickrService mockFlickrService) {
        flickrService = mockFlickrService;
    }

    public void photoSearch(String searchQuery, int resultsPage, Callback<PhotosSearch> callback) {
        flickrService.photoSearch(searchQuery, resultsPage).enqueue(callback);
    }
}
