package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.local.LocalDataSourceImpl
import com.sangjin.newproject.data.source.local.RoomDB
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single

class NaverMoviesRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) : NaverMoviesRepository {

    override fun getNaverMovies(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        remoteDataSourceImpl.getMovieData(query,
            onSuccess = { movies ->
                onSuccess(movies)
                saveMoviesCache(movies)
            },
            onFailure = { t ->
                onFailure(t)
            })
    }


    private fun saveMoviesCache(movies: List<Movie>) {
        localDataSourceImpl.saveMovieData(movies)
    }


    override fun loadCachedMovies(): Single<List<Movie>> = localDataSourceImpl.getMovieData()

}