package dev.sbeach.pictureprocurer.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import dev.sbeach.pictureprocurer.R
import kotlinx.android.synthetic.main.fragment_photo.*

private const val PHOTO_URL = "photo_url"
private const val PHOTO_TITLE = "photo_title"

/**
 * A simple [Fragment] subclass.
 * Use the [PhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhotoFragment : Fragment() {
    private var url: String? = null
    private var titleText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(PHOTO_URL)
            titleText = it.getString(PHOTO_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.text = titleText
        Picasso.get()
            .load(url)
            .into(photo)
    }

    companion object {
        const val TAG = "PhotoFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param url URL of the photo to display
         * @param title Title of the photo to display
         * @return A new instance of fragment PhotoFragment.
         */
        @JvmStatic
        fun newInstance(url: String, title: String) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(PHOTO_URL, url)
                    putString(PHOTO_TITLE, title)
                }
            }
    }
}
