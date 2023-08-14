package com.example.footballmatchresults.business.repos

import com.example.footballmatchresults.business.api.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepository<T>(var api : ApiProvider) {

    val dataEmitter : BehaviorSubject<T> = BehaviorSubject.create()
}