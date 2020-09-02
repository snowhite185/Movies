package com.example.movies.di.modules

import com.example.movies.data.ApiBuilder
import com.example.movies.data.MovieRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Anusha K on 2019-10-24.
 */
@Module
class RepositoryModule {

    @Provides
    fun provideMovieRepository(apiBuilder: ApiBuilder): MovieRepository {
        return MovieRepository(apiBuilder)
    }

}