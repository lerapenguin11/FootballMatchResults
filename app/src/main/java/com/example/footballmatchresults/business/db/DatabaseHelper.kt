package com.example.footballmatchresults.business.db

import android.content.Context
import androidx.room.Room

object DatabaseHelper {
    private lateinit var appDatabase: AppDatabase

    fun init(context: Context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "points-db").build()
    }

    fun getAllNotes(): List<Points> {
        return appDatabase.pointDao().getAllPoints()
    }

    fun insert(point: Points) {
        appDatabase.pointDao().insert(point)
    }
}