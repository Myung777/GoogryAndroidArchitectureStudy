package com.example.kyudong3.network

import com.example.kyudong3.BuildConfig

class NaverApiServiceImpl {
    private val naverService: NaverApiService

    private val clientId: String = BuildConfig.CLIENT_ID
    private val clientSecret: String = BuildConfig.CLIENT_SECRET

    init {
        naverService = RetrofitClient.getNaverService()
    }

    fun getSearchMovie(searchQuery: String) =
        naverService.getSearchMovie(clientId, clientSecret, searchQuery)
}