package com.example.movies.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Anusha K on 28-08-2020.
 */
class MovieRepository @Inject constructor(var apiBuilder: ApiBuilder) {


    suspend fun getSearchResults(title: String): SearchResponse? {
        return suspendCoroutine {
            apiBuilder.getService(Services::class.java).search(title)
                .enqueue(object : Callback<SearchResponse?> {
                    override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                        t.printStackTrace()
                        it.resume(null)
                    }

                    override fun onResponse(
                        call: Call<SearchResponse?>,
                        response: Response<SearchResponse?>
                    ) {
                        it.resume(response.body())
                    }

                })
        }

    }

    suspend fun getMovieDetails(title: String): DetailsResponse? {
        return suspendCoroutine {
            apiBuilder.getService(Services::class.java).getMovie(title)
                .enqueue(object : Callback<DetailsResponse?> {
                    override fun onFailure(call: Call<DetailsResponse?>, t: Throwable) {
                        t.printStackTrace()
                        it.resume(null)
                    }

                    override fun onResponse(
                        call: Call<DetailsResponse?>,
                        response: Response<DetailsResponse?>
                    ) {
                        it.resume(response.body())
                    }
                })
        }
    }
}