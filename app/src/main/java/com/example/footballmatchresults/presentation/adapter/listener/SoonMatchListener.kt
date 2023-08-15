package com.example.footballmatchresults.presentation.adapter.listener

import com.example.footballmatchresults.business.models.slide.SoonMatchModel

interface SoonMatchListener {

    fun soonMatchList(list : SoonMatchModel)
}