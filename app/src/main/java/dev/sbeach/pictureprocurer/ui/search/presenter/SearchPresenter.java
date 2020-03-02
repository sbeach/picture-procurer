package dev.sbeach.pictureprocurer.ui.search.presenter;

import dev.sbeach.pictureprocurer.ui.search.ISearchView;

public interface SearchPresenter {

    void onAttach(ISearchView view);

    void onDetach();

    void onSearchSubmitted(String searchQuery);

}
