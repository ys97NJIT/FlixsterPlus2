package com.example.flixsterplus2

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("name")
    val name: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double
)
