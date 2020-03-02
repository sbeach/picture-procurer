package dev.sbeach.pictureprocurer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import dev.sbeach.pictureprocurer.data.model.flickr.Photo
import dev.sbeach.pictureprocurer.ui.photo.PhotoFragment
import dev.sbeach.pictureprocurer.ui.search.SearchFragment

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
        val url = if (item.original != null && item.original.isNotEmpty())
            item.original
        else
            item.large
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
