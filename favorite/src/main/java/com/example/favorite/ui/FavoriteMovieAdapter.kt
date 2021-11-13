package com.example.favorite.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.MovieTv
import com.example.core.kit.BasePagedListAdapter
import com.example.core.utils.ext.observe
import com.example.favorite.R
import com.example.favorite.databinding.ItemMovieFavBinding

class  FavoriteMovieAdapter : BasePagedListAdapter<MovieTv, ItemMovieFavBinding>(DIFF_CALLBACK) {

    lateinit var viewModel: FavoriteViewModel
    lateinit var lifecycleOwner: LifecycleOwner

    var favoriteListener: ((item: MovieTv, isFavorite: Boolean) -> Unit)? = null
    var shareListener: ((item: MovieTv) -> Unit)? = null

    override fun getLayout(): Int = R.layout.item_movie_fav

    override fun onBindViewHolder(holder: BasePagedListAdapter.Companion.BaseViewHolder<ItemMovieFavBinding>, position: Int) {
        val item = getItem(position) ?: return
        holder.apply {
            binding.root.setOnClickListener { listener?.invoke(it, position, item) }
            lifecycleOwner.observe(viewModel.isFavorite(item)) { isFavorite ->
                binding.cbIsFav.setOnClickListener {
                    favoriteListener?.invoke(item, isFavorite)
                }
                binding.apply {
                    setVariable(BR.isFavorite, isFavorite)
                    executePendingBindings()
                }
            }

            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieTv>() {
            override fun areContentsTheSame(oldItem: MovieTv, newItem: MovieTv): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MovieTv, newItem: MovieTv): Boolean {
                return oldItem == newItem
            }
        }
    }
}