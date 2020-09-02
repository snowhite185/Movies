package com.example.movies.di.modules

import com.emeron.di.scope.FragmentScope
import com.example.movies.ui.DetailsFragment
import com.example.movies.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anusha K on 30-08-2020.
 */
@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideDetailsFragment(): DetailsFragment

}