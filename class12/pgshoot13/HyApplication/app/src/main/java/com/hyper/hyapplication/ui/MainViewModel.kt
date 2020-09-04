package com.hyper.hyapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.repository.NaverRepository

class MainViewModel(private val movieRepository: NaverRepository) : ViewModel() {
    val movieName = MutableLiveData<String>()
    private val _message = MutableLiveData<Throwable>()
    private val _emptyData = MutableLiveData<Unit>()
    private val _movieList = MutableLiveData<List<ResultGetSearchMovie.Item>>()

    val message: LiveData<Throwable> = _message
    val emptyData: LiveData<Unit> = _emptyData
    val movieList: LiveData<List<ResultGetSearchMovie.Item>> = _movieList

    fun movieSearch() {
        val movieTitle = movieName.value.toString()
        if (movieTitle.isNullOrEmpty()) {
            _emptyData.value = Unit
        } else {
            movieRepository.movieSearch(
                movieTitle,
                success = { _movieList.value = it },
                failure = { _message.value = it })
        }
    }
}