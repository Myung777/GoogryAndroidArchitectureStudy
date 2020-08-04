package com.example.study.data.remote

import com.example.study.data.model.NaverApiData

interface RemoteDataSource {
    fun getSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    )
}