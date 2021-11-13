package com.example.movieapp.ui.components.movie

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Resource
import com.example.core.domain.model.MovieTv
import com.example.core.kit.BaseFragment
import com.example.core.utils.ext.*
import com.example.movieapp.MyApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.ui.ViewModelFactory
import com.example.movieapp.ui.components.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>({ FragmentMovieBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieViewModel by viewModels { factory }
    private val adapter by lazy { MovieAdapter() }

    @ExperimentalCoroutinesApi
    override fun FragmentMovieBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            rvMovie.adapter = this@MovieFragment.adapter
            rvMovie.hasFixedSize()
            rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    appbar.isSelected = recyclerView.canScrollVertically(-1)
                }
            })
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }
            })
        }

        adapter.lifecycleOwner = this@MovieFragment
        adapter.viewModel = this@MovieFragment.viewModel
        adapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        adapter.favoriteListener = { item, isFavorite ->
            viewModel.setToFavorite(item, isFavorite)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.movies, ::handleMovies)
        observe(viewModel.search) { searchResult ->
            observe(searchResult?.asLiveData(), ::handleSearch)
        }
    }

    private fun handleMovies(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    adapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        adapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    private fun handleSearch(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    adapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        adapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
}