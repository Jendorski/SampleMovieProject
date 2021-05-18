package com.example.samplemoviesproject.ui.main.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.samplemoviesproject.R
import com.example.samplemoviesproject.data.remote.api.NetworkService.Companion.THE_MOVIES_DB_IMAGE_PATH
import com.example.samplemoviesproject.databinding.ItemMovieBinding
import com.example.samplemoviesproject.model.Movie

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 */
class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, onItemClicked: (Movie, ImageView) -> Unit) {

        binding.movieTitle.text = movie.title

        binding.movieAuthor.text = movie.original_title

        binding.imageView.load(THE_MOVIES_DB_IMAGE_PATH + movie.poster_path) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(movie, binding.imageView)
        }

    }

}
