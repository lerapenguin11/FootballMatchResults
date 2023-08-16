package com.example.footballmatchresults.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballmatchresults.business.models.leagueProfile.Data
import com.example.footballmatchresults.business.models.leagueProfile.LeagueProfileModel
import com.example.footballmatchresults.business.models.slide.PointModel
import com.example.footballmatchresults.business.models.slide.SoonMatchModel
import com.example.footballmatchresults.business.repos.LeagueProfileRepository
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
class LeagueResultsViewModel(private val repository: LeagueProfileRepository) : ViewModel() {

    val leagueList = MutableLiveData<List<Data>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable

    var pointsList = mutableListOf<PointModel>()
    val gson = Gson()
    val json = gson.toJson(pointsList)

    fun getLeagueResult(id : String) {
        val response = repository.getReloadDataLeagueProfile(id = id)
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getLeaguesResultListObserver())
    }

    fun getSoonMatchSlider() : LiveData<MutableList<SoonMatchModel>> {
        val mutableData = MutableLiveData<MutableList<SoonMatchModel>>()
        repository.getSoonMachListFootball().observeForever {list ->
            mutableData.value = list
        }

        return mutableData
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