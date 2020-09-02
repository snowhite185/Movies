package com.example.movies.di.modules

import android.app.Application
import android.content.Context
import com.example.movies.data.ApiBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Anusha K on 2019-10-23.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiBuilder(): ApiBuilder {
        return ApiBuilder()
    }
}