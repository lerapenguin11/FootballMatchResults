package com.example.footballmatchresults.business.models.slide

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

    fun getSoonMatch() : MutableList<SoonMatchModel>{
        val soonMatchList = mutableListOf<SoonMatchModel>()

        val match1 = SoonMatchModel("August 22, 2023", "Man United", "Chelsea FC", "???", "???")
        soonMatchList.add(match1)

        val match2 = SoonMatchModel("August 25, 2023", "Man United", "Chelsea FC", "???", "???")
        soonMatchList.add(match2)

        val match3 = SoonMatchModel("August 30, 2023", "Man United", "Chelsea FC", "???", "???")
        soonMatchList.add(match3)

        return soonMatchList
    }
}