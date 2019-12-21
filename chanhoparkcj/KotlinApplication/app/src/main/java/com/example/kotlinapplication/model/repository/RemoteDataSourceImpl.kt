package com.example.kotlinapplication.model.repository

import com.example.kotlinapplication.model.*
import com.example.kotlinapplication.network.RetrofitClient
import com.example.kotlinapplication.network.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl {
    private var service: RetrofitService = RetrofitClient.client

    fun getMovieCall(query: String): Single<ResponseItems<MovieItems>> {
        return service.getMovieCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    fun getImageCall(query: String): Single<ResponseItems<ImageItems>> {
        return service.getImageCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    fun getBlogCall(query: String): Single<ResponseItems<BlogItems>> {
        return service.getBlogCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

    fun getKinCall(query: String): Single<ResponseItems<KinItems>> {
        return service.getKinCall(query)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
    }

}