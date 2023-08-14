package com.example.footballmatchresults.business.repos

import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.api.FootballApi.Companion.retrofitService

class LeagueRepository(private val api: FootballApi) {

    fun getAllMovies() = retrofitService!!.getLeague()

    /*@SuppressLint("CheckResult")
    fun reloadData(callback: Callback<List<Data>>) {

        api.providerFootballApi().getLeague()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<LeagueModel>(){
                override fun onNext(t: LeagueModel) {
                    callback.returnResult(t.data)
                    println("HOME_REPOSITORY" + callback.returnResult(t.data))
                }

                override fun onError(e: Throwable) {
                    Log.d("MAIN_REPO", "error: $e")
                }

                override fun onComplete() {
                    //нне реализуется
                }
            })
    }*/
}