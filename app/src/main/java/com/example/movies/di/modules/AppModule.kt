package com.example.movies.di.modules

import android.app.Application
import com.example.movies.MovieApplication
import dagger.Binds
import dagger.Module

/**
 * Created by Anusha K on 2019-10-30.
 */

@Module
interface AppModule {

    @Binds
    fun bindApplication(app: MovieApplication): Application

}