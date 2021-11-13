package com.example.core.di

import dagger.Binds
import dagger.Module
import com.example.core.data.source.MovieDBRepository
import com.example.core.domain.repository.IMovieTvRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Suppress("unused")
  @Binds
  abstract fun provideRepository(movieDBRepository: MovieDBRepository): IMovieTvRepository
}