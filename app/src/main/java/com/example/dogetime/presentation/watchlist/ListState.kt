package com.example.dogetime.presentation.watchlist

import com.example.dogetime.data.local.entities.WatchListMedia

data class ListState(
    val media: List<WatchListMedia> = mutableListOf()
//    val type: String = "movies",
//    val movies: List<Movie> = mutableListOf(),
//    val series: List<Movie> = mutableListOf(),
//    val accountId: Int? = null,
//    val sessionId: String? = null

)
