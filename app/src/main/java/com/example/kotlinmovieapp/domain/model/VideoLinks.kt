package com.example.kotlinmovieapp.domain.model

data class VideoLinks(
    val hd: Map<String, String>?,
    val fhd: Map<String, String>?,
    val sd: Map<String, String>?
)
