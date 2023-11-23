package com.example.kotlinmovieapp.presentation.navgraph

sealed class Route (
    val route: String

) {
    object Home : Route("home")
    object Search : Route("search")

    object Browse : Route("browse")

    object Favorites : Route("favorites")

    object Account : Route("account")

    object VideoPlayer: Route("video_player/{id}/{season}/{episode}")

    object MovieDetails: Route("movie/{id}")

    object ShowDetails: Route("show/{id}")


}
