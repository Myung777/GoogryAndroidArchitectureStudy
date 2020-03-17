package com.example.myapplication.ui

import com.example.myapplication.data.repository.MovieRepositoryDataSet

/**
 * Presenter : 로직, 계산, if문
 * */
class SearchMoviePresenter(private val view: Contract.View, private val movieRepositoryDataSet: MovieRepositoryDataSet): Contract.Presenter {

    override fun searchMovie(etMovieTitle:String) {
        if (etMovieTitle.isNotEmpty()) {
            movieRepositoryDataSet.movieRepository.getMovieList(
                etMovieTitle,
                success = { view.addItems(it) },
                failed = { view.recordLog(it.toString()) })

        } else {
            view.showToastMovieTitleIsEmpty()
        }
    }
}