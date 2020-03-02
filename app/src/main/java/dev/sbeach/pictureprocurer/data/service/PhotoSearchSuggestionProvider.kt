package dev.sbeach.pictureprocurer.data.service

import android.content.SearchRecentSuggestionsProvider

class PhotoSearchSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "dev.sbeach.PhotoSearchSuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}
