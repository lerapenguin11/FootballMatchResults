package com.example.footballmatchresults.viewModel

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import com.example.footballmatchresults.PREFS_KEY
import com.example.footballmatchresults.business.models.slide.PointModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PointViewModel(application: Application) : AndroidViewModel(application){

    val sharedPreferences = application.getSharedPreferences("shared preferences", MODE_PRIVATE)

    // creating a variable for gson.
    val gson = Gson()

    // below line is to get to string present from our
    // shared prefs if not present setting it as null.
    val json = sharedPreferences.getString("courses", null)

    // below line is to get the type of our array list.
    val type: Type = object : TypeToken<ArrayList<PointModel?>?>() {}.type

    var courseList = gson.fromJson<Any>(json, type) as ArrayList<PointModel>

    fun addItemToList(home: String, away : String) {
        // in this method we are adding item to list and
        // notifying adapter that data has changed
        courseList.add(PointModel(home, away))
    }
}