package com.example.footballmatchresults.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.models.leagueProfile.Data

class LeagueProfileAdapter : RecyclerView.Adapter<LeagueProfileAdapter.LeagueProfileViewHolder>() {

    private val leagueList =  mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other_match_league, parent, false)

        return LeagueProfileViewHolder(view)
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: LeagueProfileViewHolder, position: Int) {
        val resultLeagueList : Data = leagueList[position]

        holder.flagHome.setImageResource(R.drawable.manchester)
        holder.flagAway.setImageResource(R.drawable.manchester)
        holder.nameHome.text = resultLeagueList.homeName
        holder.nameAway.text = resultLeagueList.awayName
        holder.resultHome.text = resultLeagueList.homeScore.toString()
        holder.resultAway.text = resultLeagueList.awayScore.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultLeagueList : List<Data>){
        this.leagueList.clear()
        this.leagueList.addAll(resultLeagueList)
        notifyDataSetChanged()
    }

    class LeagueProfileViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val flagHome : ImageView = view.findViewById(R.id.ic_flag_home)
        val flagAway : ImageView = view.findViewById(R.id.ic_flag_away)
        val nameHome : TextView = view.findViewById(R.id.tv_name_home)
        val nameAway : TextView = view.findViewById(R.id.tv_name_away)
        val resultHome : TextView = view.findViewById(R.id.tv_result_home)
        val resultAway : TextView = view.findViewById(R.id.tv_result_away)
    }
}