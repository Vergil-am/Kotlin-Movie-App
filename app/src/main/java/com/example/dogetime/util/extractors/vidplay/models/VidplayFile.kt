package com.example.dogetime.util.extractors.vidplay.models


data class Source(
    val `file`: String
)
data class Track(
    val `file`: String,
    val kind: String
)

data class Result(
    val sources: List<Source>,
    val tracks: List<Track>
)
data class VidplayFile(
    val status: Int,
    val result: Result
)