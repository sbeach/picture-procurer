package dev.sbeach.pictureprocurer.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dev.sbeach.pictureprocurer.R;
import dev.sbeach.pictureprocurer.data.model.flickr.Photo;
import dev.sbeach.pictureprocurer.ui.search.presenter.SearchPresenter;
import dev.sbeach.pictureprocurer.ui.search.presenter.SearchPresenterImpl;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SearchFragment extends Fragment implements ISearchView {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private SearchPresenter searchPresenter;
    private EditText searchInput;
    private TextView searchNoResults;
    private RecyclerView searchResultsRecycler;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchFragment() {
        searchPresenter = new SearchPresenterImpl();
    }

    public static SearchFragment newInstance(int columnCount) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchInput = root.findViewById(R.id.searchInput);
        searchNoResults = root.findViewById(R.id.searchNoResults);
        searchResultsRecycler = root.findViewById(R.id.searchResults);

        searchInput.requestFocus();
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                searchPresenter.onSearchSubmitted(v.getText().toString());
                Snackbar.make(root, "Search submitted: " + v.getText(), Snackbar.LENGTH_SHORT).show();
                handled = true;
            }
            return handled;
        });

        return root;
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        searchPresenter.onAttach(this);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        searchPresenter.onDetach();
        mListener = null;
    }

    @Override
    public void displaySearchResults(List<Photo> photos) {
        if (searchResultsRecycler != null) {
            Context context = searchResultsRecycler.getContext();
            if (mColumnCount <= 1) {
                searchResultsRecycler.setLayoutManager(new LinearLayoutManager(context));
            } else {
                searchResultsRecycler.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            searchResultsRecycler.setVisibility(View.VISIBLE);
            searchResultsRecycler.setAdapter(new SearchResultsAdapter(photos, mListener));
        }
    }

    @Override
    public void hideSearchResults() {
        searchResultsRecycler.setVisibility(View.GONE);
    }

    @Override
    public void displayNoResults() {
        searchNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoResults() {
        searchNoResults.setVisibility(View.GONE);
    }

    @Override
    public void displayError(String message) {
        Snackbar.make(searchResultsRecycler, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Photo item);
    }
}
