package com.example.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.MovieTv
import com.example.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: MovieTvUseCase?) : ViewModel() {
    val favoriteMovies = useCase?.getFavoriteMovies()
    val favoriteTvShows = useCase?.getFavoriteTvShows()

    fun isFavorite(movieTv: MovieTv) = useCase?.isFavorite(movieTv)?.asLiveData()
    fun setToFavorite(movieTv: MovieTv, saved: Boolean) = useCase?.setFavoriteMovieTv(movieTv, saved)
}