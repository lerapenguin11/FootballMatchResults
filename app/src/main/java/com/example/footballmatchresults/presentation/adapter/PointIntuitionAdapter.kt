package com.example.footballmatchresults.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.db.Points
import com.example.footballmatchresults.business.models.slide.PointModel

class PointIntuitionAdapter() : RecyclerView.Adapter<PointIntuitionAdapter.PointIntuitionViewHolder>() {

    private val pointList = mutableListOf<Points>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointIntuitionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_point, parent, false)

        return PointIntuitionViewHolder(view)
    }

    override fun getItemCount(): Int = pointList.size

    override fun onBindViewHolder(holder: PointIntuitionViewHolder, position: Int) {
        val resultPoint = pointList[position]

        holder.resultHome.text = resultPoint.pointHome
        holder.resultAway.text = resultPoint.pointAway
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultLeagueList: List<Points>){
        //this.pointList.clear()
        this.pointList.addAll(resultLeagueList)
        notifyDataSetChanged()
    }

    class PointIntuitionViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val resultHome : TextView = view.findViewById(R.id.tv_result_home)
        val resultAway : TextView = view.findViewById(R.id.tv_result_away)
    }
}