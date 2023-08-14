package com.example.footballmatchresults.business.api

abstract class Callback<T> {
    abstract fun returnResult(t: T)
    abstract fun returnError(error : Throwable)
}