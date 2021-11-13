package com.example.movieapp.di

import com.example.core.di.CoreComponent
import com.example.movieapp.ui.components.MainActivity
import com.example.movieapp.ui.components.detail.DetailActivity
import com.example.movieapp.ui.components.movie.MovieFragment
import com.example.movieapp.ui.components.tv.TvFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvFragment)
}