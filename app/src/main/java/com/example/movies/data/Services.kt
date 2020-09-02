package com.example.movies.data

import com.example.movies.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Anusha K on 29-08-2020.
 */
interface Services {


    @GET("/")
    fun search(
        @Query("s") title: String,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): Call<SearchResponse?>

    @GET("/")
    fun getMovie(
        @Query("t") title: String,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailsResponse?>

}

