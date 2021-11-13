package com.example.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.core.data.Resource
import com.example.core.domain.model.MovieTv
import kotlinx.coroutines.flow.Flow

interface IMovieTvRepository {
    fun getMovies(): Flow<Resource<List<MovieTv>>>
    fun getTvShows(): Flow<Resource<List<MovieTv>>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieTv>>
    fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>>
    fun searchMovies(query: String): Flow<Resource<List<MovieTv>>>
    fun searchTvShows(query: String): Flow<Resource<List<MovieTv>>>
    fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean)
    fun isFavorite(movieTv: MovieTv): Flow<Boolean>
}