package com.example.footballmatchresults.business.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.footballmatchresults.business.api.FootballApi
import com.example.footballmatchresults.business.api.FootballApi.Companion.retrofitService
import com.example.footballmatchresults.business.models.Constants
import com.example.footballmatchresults.business.models.NewsModel

class LeagueRepository(private val api: FootballApi) {

    fun getReloadDataLeague() = retrofitService!!.getLeague()

    fun getNewsListFootball() : LiveData<MutableList<NewsModel>> {
        val newsList = Constants.getNewsList()
        val mutableData = MutableLiveData<MutableList<NewsModel>>()
        val list = mutableListOf<NewsModel>()

        for (i in newsList){
            val id = i.id
            val title = i.title
            val date = i.date
            val text = i.text

            val listQuizModel = NewsModel(
                id = id,
                title = title,
                date = date,
                text = text
            )
            list.add(listQuizModel)
        }

        mutableData.value = list

        return mutableData
    }
}