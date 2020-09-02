package com.example.movies.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Anusha K on 30-08-2020.
 *
 */
class SearchItemResponse {

    @SerializedName("Title")
    var title: String? = null

    @SerializedName("Year")
    var year: String? = null

    @SerializedName("imdbID")
    var imdbID: String? = null

    @SerializedName("Type")
    var type: String? = null

    @SerializedName("Poster")
    var poster: String? = null
}

class SearchResponse {

    @SerializedName("Search")
    var searchResult: ArrayList<SearchItemResponse>? = null

    @SerializedName("totalResults")
    var totalResults: String? = null

}