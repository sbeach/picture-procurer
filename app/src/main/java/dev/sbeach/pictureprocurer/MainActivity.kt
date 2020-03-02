package dev.sbeach.pictureprocurer

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import dev.sbeach.pictureprocurer.data.model.flickr.Photo
import dev.sbeach.pictureprocurer.ui.photo.PhotoFragment
import dev.sbeach.pictureprocurer.ui.search.SearchFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), SearchFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        var searchQuery = ""
        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchQuery = query
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance(2, searchQuery))
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }

        return true
    }

    override fun onListFragmentInteraction(item: Photo) {
        val url = when {
            !item.original.isNullOrBlank() -> item.original
            !item.large.isNullOrBlank() -> item.large
            else -> {
                Snackbar.make(container, R.string.large_size_unavailable, Snackbar.LENGTH_SHORT).show()
                return
            }
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.container, PhotoFragment.newInstance(url), PhotoFragment.TAG)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onBackPressed() {
        val photoFragment = supportFragmentManager.findFragmentByTag(PhotoFragment.TAG)
        if (photoFragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(photoFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        } else {
            super.onBackPressed()
        }
    }
}
