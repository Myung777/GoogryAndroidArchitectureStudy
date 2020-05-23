package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.Retrofit
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {

    override val service = Retrofit.service
    override val disposable = CompositeDisposable()

    override fun getMovieList(
        keyWord: String,
        Success: (Single<NaverApiData>) -> Unit,
        Failure: (t: Throwable) -> Unit
    ) {
        Success.invoke(service.getMovies(keyWord))
    }
}