package com.example.samplemoviesproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemoviesproject.databinding.ItemMovieBinding
import com.example.samplemoviesproject.model.Movie
import com.example.samplemoviesproject.ui.main.viewholder.MovieViewHolder

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Movie] along with [MovieViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class MovieListAdapter(
    private val onItemClicked: (Movie, ImageView) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}
