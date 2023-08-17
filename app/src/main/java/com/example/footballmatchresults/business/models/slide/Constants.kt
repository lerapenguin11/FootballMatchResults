package com.example.footballmatchresults.business.models.slide

import com.example.footballmatchresults.business.models.leagueProfileLogo.Data

object Constants {

    fun getNewsList() : MutableList<NewsModel> {
        val newsList = mutableListOf<NewsModel>()

        val new1 = NewsModel(0,"Chelsea will pay 53+5 million pounds for Lavia. Medical examination and announcement – this week", "15 august, 2023",
            "Romeo Lavia will be introduced as a Chelsea rookie this week.\n" +
                    "According to an insider Fabrizio Romano, the Blues will pay Southampton 53 million pounds, another 5 million are provided as bonuses.\n" +
                    "In the coming days, the midfielder will undergo a medical examination.\n" +
                    "The Belgian played 29 games in the Premier League last season and scored one goal. Detailed statistics of the 19-year-old football player can be found here.")
        newsList.add(new1)

        val new2 = NewsModel(1,"The history of football", "14 august, 2023", "Romeo Lavia will be introduced as a Chelsea rookie this week.\n" +
                "According to an insider Fabrizio Romano, the Blues will pay Southampton 53 million pounds, another 5 million are provided as bonuses.\n" +
                "In the coming days, the midfielder will undergo a medical examination.\n" +
                "The Belgian played 29 games in the Premier League last season and scored one goal. Detailed statistics of the 19-year-old football player can be found here.")
        newsList.add(new2)

        val new3 = NewsModel(2,"National teams", "13 august, 2023",  "Romeo Lavia will be introduced as a Chelsea rookie this week.\n" +
                "According to an insider Fabrizio Romano, the Blues will pay Southampton 53 million pounds, another 5 million are provided as bonuses.\n" +
                "In the coming days, the midfielder will undergo a medical examination.\n" +
                "The Belgian played 29 games in the Premier League last season and scored one goal. Detailed statistics of the 19-year-old football player can be found here.")
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

    fun getLogo() : MutableList<Data>{
        val listLogo = mutableListOf<Data>()
        val logo1 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell")
        listLogo.add(logo1)

        val logo2 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724129651.png?win007=sell")
        listLogo.add(logo2)

        val logo3 = Data("http://zq.titan007.com/Image/team/images/9/1grd5f04ex2k.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724122951.png?win007=sell")
        listLogo.add(logo3)

        val logo4 = Data("http://zq.titan007.com/Image/team/images/20071271212516190.gif?win007=sell", "http://zq.titan007.com/Image/team/images/165724143142.png?win007=sell")
        listLogo.add(logo4)

        val logo5 = Data("http://zq.titan007.com/Image/team/images/9/1grd5f04ex2k.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724129651.png?win007=sell")
        listLogo.add(logo5)

        val logo6 = Data("http://zq.titan007.com/Image/team/images/20130406101859.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724146081.png?win007=sell")
        listLogo.add(logo6)

        val logo7 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724122951.png?win007=sell")
        listLogo.add(logo7)

        val logo8 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/165724129651.png?win007=sell")
        listLogo.add(logo8)

        val logo9 = Data("http://zq.titan007.com/Image/team/images/20071271212516190.gif?win007=sell", "http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell")
        listLogo.add(logo9)

        val logo10 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/20071271212516190.gif?win007=sell")
        listLogo.add(logo10)

        val logo11 = Data("http://zq.titan007.com/Image/team/images/7/1h6z1xbyqap.png?win007=sell", "http://zq.titan007.com/Image/team/images/20071271212516190.gif?win007=sell")
        listLogo.add(logo11)

        return listLogo
    }
}