package com.example.footballmatchresults.business.api

import com.example.footballmatchresults.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider {

    val football : Retrofit by lazy { initApi() }

    fun initApi() =  Retrofit.Builder ()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun providerFootballApi() : FootballApi = football.create(FootballApi :: class.java)
}