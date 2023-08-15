package com.example.footballmatchresults.business.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.models.slide.Constants
import com.example.footballmatchresults.business.models.slide.NewsModel
import com.example.footballmatchresults.business.models.slide.SoonMatchModel

class LeagueProfileRepository(private val api: FootballApi) {

    fun getReloadDataLeagueProfile(id : String) = FootballApi.retrofitService!!.getLeagueResult(leagueId = id)

    fun getSoonMachListFootball() : LiveData<MutableList<SoonMatchModel>> {
        val newsList = Constants.getSoonMatch()
        val mutableData = MutableLiveData<MutableList<SoonMatchModel>>()
        val list = mutableListOf<SoonMatchModel>()

        for (i in newsList){
            val date = i.date
            val nameHome = i.nameHome
            val nameAway = i.nameAway
            val resultHome = i.resultHome
            val resultAway = i.resultAway

            val listQuizModel = SoonMatchModel(
                date = date,
                nameHome = nameHome,
                nameAway = nameAway,
                resultHome = resultHome,
                resultAway = resultAway
            )
            list.add(listQuizModel)
        }

        mutableData.value = list

        return mutableData
    }
}