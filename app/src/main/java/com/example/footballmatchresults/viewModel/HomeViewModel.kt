package com.example.footballmatchresults.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballmatchresults.business.models.NewsModel
import com.example.footballmatchresults.business.models.league.Data
import com.example.footballmatchresults.business.models.league.LeagueModel
import com.example.footballmatchresults.business.repos.LeagueRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val repository: LeagueRepository) : ViewModel(){

    val leagueList = MutableLiveData<List<Data>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable

    fun getLeague() {
        val response = repository.getReloadDataLeague()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getLeaguesListObserver())
    }

    fun getNewsSlider() : LiveData<MutableList<NewsModel>> {
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        repository.getNewsListFootball().observeForever {list ->
            mutableData.value = list
        }

        return mutableData
    }

    private fun getLeaguesListObserver(): Observer<LeagueModel> {
        return object : Observer<LeagueModel> {
            override fun onComplete() {
                //hide progress indicator .
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onError(e: Throwable) {
                leagueList.postValue(null)
            }

            override fun onNext(t: LeagueModel) {
                leagueList.postValue(t.data)
                println(t.code)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                //start showing progress indicator.
            }
        }
    }
}