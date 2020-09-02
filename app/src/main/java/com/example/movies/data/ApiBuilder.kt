package com.example.movies.data

import com.example.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by Anusha K on 2019-11-13.
 *
 * Retrofit builder class
 *
 */
class ApiBuilder @Inject constructor() {

    private var retrofitBuilder: Retrofit.Builder
    private var retrofit: Retrofit

    init {
        retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
        retrofit = retrofitBuilder.build()
    }

    fun <T> getService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    companion object {

        private const val BASE_URL = BuildConfig.BASE_URL
    }
}