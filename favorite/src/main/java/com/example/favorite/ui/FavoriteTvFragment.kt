package com.example.favorite.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.core.domain.model.MovieTv
import com.example.core.kit.BaseFragment
import com.example.core.utils.ext.gone
import com.example.core.utils.ext.observe
import com.example.core.utils.ext.visible
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavoriteTvBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.movieapp.ui.ViewModelFactory
import com.example.movieapp.ui.components.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FavoriteTvFragment :
    BaseFragment<FragmentFavoriteTvBinding>({ FragmentFavoriteTvBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }
    private val viewModel: FavoriteViewModel by viewModels { factory }
    private val adapter by lazy { FavoriteMovieAdapter() }

    override fun FragmentFavoriteTvBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvFavoriteTv?.adapter = this@FavoriteTvFragment.adapter
        adapter.lifecycleOwner = this@FavoriteTvFragment
        adapter.viewModel = this@FavoriteTvFragment.viewModel
        adapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        adapter.favoriteListener = { item, isFavorite ->
            viewModel.setToFavorite(item, isFavorite)
            binding?.apply {
                Snackbar.make(
                    root,
                    getString(R.string.deleted_favorite, getString(R.string.tv_show)),
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction(getString(R.string.undo)) {
                        viewModel.setToFavorite(item, false)
                        return@setAction
                    }
                    show()
                }
            }
        }
    }

    override fun observeViewModel() {
        observe(viewModel.favoriteTvShows, ::handleFavTvShows)
    }

    private fun handleFavTvShows(favMovies: PagedList<MovieTv>) {
        if (!favMovies.isNullOrEmpty()) {
            binding?.emptyFavorite?.root?.gone()
            binding?.rvFavoriteTv?.visible()
            adapter.submitList(favMovies)
        } else {
            binding?.emptyFavorite?.root?.visible()
            binding?.rvFavoriteTv?.gone()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }
}