package dev.sbeach.pictureprocurer.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dev.sbeach.pictureprocurer.R;
import dev.sbeach.pictureprocurer.data.model.flickr.Photo;
import dev.sbeach.pictureprocurer.ui.search.SearchFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Photo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class SearchResultsAdapter extends PagedListAdapter<Photo, SearchResultsAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getThumbnail().equals(newItem.getThumbnail()));
        }
    };
    private final List<Photo> photos;
    private final OnListFragmentInteractionListener mListener;

    public SearchResultsAdapter(List<Photo> items, OnListFragmentInteractionListener listener) {
        super(DIFF_CALLBACK);
        photos = items;
        mListener = listener;
    }

    public void addMorePhotos(List<Photo> newPhotos) {
        photos.addAll(newPhotos);
//        submitList(photos);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = getItem(position);
        holder.title.setText(holder.mItem.getTitle());
        Picasso.get()
                .load(holder.mItem.getThumbnail())
                .into(holder.image);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener && holder.mItem != null) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
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
