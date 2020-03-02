package dev.sbeach.pictureprocurer.ui.search.presenter;

import dev.sbeach.pictureprocurer.ui.search.SearchView;

public interface SearchPresenter {

    void onAttach(SearchView view);

    void onDetach();

    void onSearchSubmitted(String searchQuery);

}
