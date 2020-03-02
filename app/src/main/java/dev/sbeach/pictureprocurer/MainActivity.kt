package dev.sbeach.pictureprocurer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dev.sbeach.pictureprocurer.data.model.flickr.Photo
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

    override fun onListFragmentInteraction(item: Photo?) {
        Snackbar.make(container, "${item?.title}", Snackbar.LENGTH_SHORT).show()
    }
}
