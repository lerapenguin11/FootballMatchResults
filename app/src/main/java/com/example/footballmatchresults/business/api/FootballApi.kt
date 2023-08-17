package com.example.footballmatchresults.business.api

import com.example.footballmatchresults.BASE_URL
import com.example.footballmatchresults.business.models.league.LeagueModel
import com.example.footballmatchresults.business.models.leagueProfile.LeagueProfileModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    //Списоок лиг
    @GET("league?api_key=dDy9kCrJg2S3oUQ5")
    fun getLeague() : Observable<LeagueModel>

    @GET("schedule?api_key=dDy9kCrJg2S3oUQ5")
    fun getLeagueResult(
        @Query("leagueId") leagueId : String
    ) : Observable<LeagueProfileModel>


    companion object {

        var retrofitService: FootballApi? = null

        fun getInstance() : FootballApi {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                retrofitService = retrofit.create(FootballApi::class.java)
            }
            return retrofitService!!
        }
    }
}