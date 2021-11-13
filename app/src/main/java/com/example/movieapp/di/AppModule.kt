package com.example.movieapp.di

import com.example.core.domain.usecase.MovieTvInteractor
import com.example.core.domain.usecase.MovieTvUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Suppress("unused")
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieTvInteractor): MovieTvUseCase
}