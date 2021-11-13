package com.example.favorite.di

import dagger.Component
import com.example.movieapp.di.AppModule
import com.example.core.di.CoreComponent
import com.example.favorite.ui.FavoriteFragment
import com.example.favorite.ui.FavoriteMovieFragment
import com.example.favorite.ui.FavoriteTvFragment

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvFragment)
}