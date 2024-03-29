package com.example.dogetime.domain.repository

import com.example.dogetime.data.remote.dto.GetShowsDTO
import com.example.dogetime.data.remote.dto.MovieDetailsDTO
import com.example.dogetime.data.remote.dto.MoviesDTO
import com.example.dogetime.data.remote.dto.SearchDTO
import com.example.dogetime.data.remote.dto.SeasonDTO
import com.example.dogetime.data.remote.dto.ShowDetailsDTO

interface MovieRepository {

    // Trending
    suspend fun getTrending() : MoviesDTO
    suspend fun getTrendingShows(page : Int) : GetShowsDTO

    // Movies
    suspend fun getMovies(page : Int, catalog: String) : MoviesDTO

    suspend fun getMovie(movieId: Int) : MovieDetailsDTO


    // Shows
    suspend fun getShow(showId: Int) : ShowDetailsDTO
    suspend fun getShows(page: Int, catalog: String) : GetShowsDTO
    suspend fun getSeason(seasonId: Int, season: Int) : SeasonDTO

    // Search
    suspend fun searchMovies(query: String) : SearchDTO

    suspend fun searchShows(query: String) : GetShowsDTO


}