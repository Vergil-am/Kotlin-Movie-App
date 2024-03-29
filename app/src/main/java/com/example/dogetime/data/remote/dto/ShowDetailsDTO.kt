package com.example.dogetime.data.remote.dto

import com.example.dogetime.domain.model.CreatedBy
import com.example.dogetime.domain.model.LastEpisodeToAir
import com.example.dogetime.domain.model.Network
import com.example.dogetime.domain.model.Season
import com.example.dogetime.domain.model.Genre
import com.example.dogetime.domain.model.ProductionCompany
import com.example.dogetime.domain.model.ProductionCountry
import com.example.dogetime.domain.model.SpokenLanguage

data class ShowDetailsDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Any>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
)