package com.example.footballmatchresults.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballmatchresults.business.models.leagueProfile.Data
import com.example.footballmatchresults.business.models.leagueProfile.LeagueProfileModel
import com.example.footballmatchresults.business.repos.LeagueProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LeagueResultsViewModel(private val repository: LeagueProfileRepository) : ViewModel() {

    val leagueList = MutableLiveData<List<Data>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable


    fun getLeagueResult(id : String) {
        val response = repository.getReloadDataLeagueProfile(id = id)
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getLeaguesResultListObserver())
    }

    private fun getLeaguesResultListObserver(): Observer<LeagueProfileModel> {
        return object : Observer<LeagueProfileModel> {
            override fun onComplete() {
                //hide progress indicator .
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onError(e: Throwable) {
                leagueList.postValue(null)
            }

            override fun onNext(t: LeagueProfileModel) {
                leagueList.postValue(t.data)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                //start showing progress indicator.
            }
        }
    }
}