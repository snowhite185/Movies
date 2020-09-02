package com.example.movies.di.modules

import com.example.movies.di.scope.ActivityScope
import com.example.movies.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anusha K on 30-08-2020.
 */
@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}