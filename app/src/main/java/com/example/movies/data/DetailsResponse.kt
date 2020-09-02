package com.example.movies.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Anusha K on 01-09-2020.
 */
class DetailsResponse {

    @SerializedName("Title")
    var title: String? = null

    @SerializedName("Year")
    var year: String? = null

    @SerializedName("Released")
    var released: String? = null

    @SerializedName("Genre")
    var genre: String? = null

    @SerializedName("Director")
    var director: String? = null

    @SerializedName("Writer")
    var writer: String? = null

    @SerializedName("Plot")
    var plot: String? = null

    @SerializedName("imdbRating")
    var imdbRating: String? = null

    @SerializedName("Poster")
    var poster: String? = null

    @SerializedName("Country")
    var country: String? = null

    @SerializedName("Runtime")
    var runtime: String? = null

    @SerializedName("Rated")
    var rated: String? = null
}