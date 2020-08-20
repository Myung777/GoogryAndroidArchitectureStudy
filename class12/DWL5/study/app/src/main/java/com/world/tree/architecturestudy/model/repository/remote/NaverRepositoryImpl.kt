package com.world.tree.architecturestudy.model.repository.remote

import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.model.source.remote.NaverRemoteDataSource
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(private val remoteDataSource: NaverRemoteDataSource) :
    NaverRepository {
    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovies(q, success = success, error = error)
    }

}