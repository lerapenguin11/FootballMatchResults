package com.example.footballmatchresults.business.models.slide

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
}