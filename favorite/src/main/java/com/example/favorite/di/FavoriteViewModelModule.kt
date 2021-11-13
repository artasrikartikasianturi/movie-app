package com.example.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.movieapp.di.ViewModelKey
import com.example.movieapp.ui.ViewModelFactory
import com.example.favorite.ui.FavoriteViewModel

@Suppress("unused")
@Module
abstract class FavoriteViewModelModule {

    @Binds
    abstract fun bindFavoriteViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}