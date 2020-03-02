package dev.sbeach.pictureprocurer.ui.search;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import dev.sbeach.pictureprocurer.R;
import dev.sbeach.pictureprocurer.data.model.flickr.Photo;
import dev.sbeach.pictureprocurer.ui.search.SearchFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Photo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private final List<Photo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public SearchResultsAdapter(List<Photo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(holder.mItem.getTitle());
        Picasso.get()
                .load(holder.mItem.getThumbnail())
                .into(holder.image);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final ImageView image;
        public Photo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
        }
    }
}
