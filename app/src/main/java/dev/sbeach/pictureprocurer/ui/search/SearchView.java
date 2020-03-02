package dev.sbeach.pictureprocurer.ui.search;

import java.util.List;

import dev.sbeach.pictureprocurer.data.model.flickr.Photo;

public interface SearchView {

    void displaySearchResults(List<Photo> photos);

    void displayError(String message);

}
