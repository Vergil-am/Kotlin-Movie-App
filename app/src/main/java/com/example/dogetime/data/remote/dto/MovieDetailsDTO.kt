package com.example.dogetime.data.remote.dto

import com.example.dogetime.domain.model.BelongsToCollection
import com.example.dogetime.domain.model.Genre
import com.example.dogetime.domain.model.ProductionCompany
import com.example.dogetime.domain.model.ProductionCountry
import com.example.dogetime.domain.model.SpokenLanguage

data class MovieDetailsDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)