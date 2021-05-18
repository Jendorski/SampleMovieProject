package com.example.samplemoviesproject.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplemoviesproject.data.repository.MoviesRepository
import com.example.samplemoviesproject.model.Movie
import com.example.samplemoviesproject.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    private val _moviesListData = MutableLiveData<State<List<Movie>>>()

    val moviesLiveData: LiveData<State<List<Movie>>>
        get() = _moviesListData

    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getAllMovies().collect {
                _moviesListData.value = it
            }
        }
    }
}

