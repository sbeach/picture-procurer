package dev.sbeach.pictureprocurer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance(2))
                .commitNow()
        }
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
