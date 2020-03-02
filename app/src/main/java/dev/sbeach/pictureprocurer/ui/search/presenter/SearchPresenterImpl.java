package dev.sbeach.pictureprocurer.ui.search.presenter;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dev.sbeach.pictureprocurer.data.model.flickr.Photo;
import dev.sbeach.pictureprocurer.data.remote.response.flickr.PhotosSearch;
import dev.sbeach.pictureprocurer.data.service.ServiceController;
import dev.sbeach.pictureprocurer.ui.search.SearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenterImpl implements SearchPresenter {

    private String TAG = SearchPresenterImpl.class.getSimpleName();
    private SearchView searchView = new EmptySearchView();

    public void onAttach(SearchView view) {
        searchView = view;
    }

    public void onDetach() {
        searchView = new EmptySearchView();
    }

    public void onSearchSubmitted(String searchQuery) {
        ServiceController controller = new ServiceController();
        controller.photoSearch(searchQuery, 1, new SearchCallback());
    }

    class SearchCallback implements Callback<PhotosSearch> {

        public void onResponse(@NotNull Call<PhotosSearch> call, @NotNull Response<PhotosSearch> response) {
            List<Photo> photos = new ArrayList<>();
            if (response.body() != null) {
                photos = response.body().getPhotos().getPhotos();
            }
            searchView.displaySearchResults(photos);
        }

        public void onFailure(@NotNull Call<PhotosSearch> call, @NotNull Throwable t) {
            searchView.displayError(t.getMessage());
        }
    }

    private class EmptySearchView implements SearchView {

        private String TAG = EmptySearchView.class.getSimpleName();

        public void displaySearchResults(List<Photo> photos) {
            Log.d(TAG, "displaySearchResults");
        }

        @Override
        public void displayError(String message) {
            Log.d(TAG, "displayError");
        }
    }
}
