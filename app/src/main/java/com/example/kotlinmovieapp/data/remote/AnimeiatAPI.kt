package com.example.kotlinmovieapp.data.remote

import com.example.kotlinmovieapp.data.remote.dto.AnimeiatDTO
import com.example.kotlinmovieapp.data.remote.dto.AnimeiatDetailsDTO
import com.example.kotlinmovieapp.data.remote.dto.AnimeiatEpisodesDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeiatAPI {

    @GET("anime")
   suspend fun getPopularAnime() : AnimeiatDTO
   @GET("anime/{slug}")
   suspend fun getAnimeDetails(
      @Path("slug") slug: String
   ) : AnimeiatDetailsDTO

   @GET("anime/{slug}/episodes")
   suspend fun getEpisodes(
       @Path("slug") slug: String
   ) : AnimeiatEpisodesDTO
}