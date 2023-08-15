package com.example.footballmatchresults.business.models

object Constants {

    fun getNewsList() : MutableList<NewsModel> {
        val newsList = mutableListOf<NewsModel>()

        val new1 = NewsModel(0,"Famous football clubs", "15 august", "football")
        newsList.add(new1)

        val new2 = NewsModel(1,"The history of football", "14 august", "football")
        newsList.add(new2)

        val new3 = NewsModel(2,"National teams", "13 august",  "football")
        newsList.add(new3)

        return newsList
    }
}