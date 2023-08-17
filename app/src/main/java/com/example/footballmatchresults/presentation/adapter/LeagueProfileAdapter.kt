package com.example.footballmatchresults.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.models.leagueProfile.Data
import com.example.footballmatchresults.business.models.slide.Constants
import com.example.footballmatchresults.presentation.adapter.listener.LeagueInformationListener

class LeagueProfileAdapter(val leagueInfoListener : LeagueInformationListener) : RecyclerView.Adapter<LeagueProfileAdapter.LeagueProfileViewHolder>() {

    private val leagueList =  mutableListOf<Data>()
    private lateinit var resultLogo : com.example.footballmatchresults.business.models.leagueProfileLogo.Data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other_match_league, parent, false)

        return LeagueProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return leagueList.size
    }

    override fun onBindViewHolder(holder: LeagueProfileViewHolder, position: Int) {
        val resultLeagueList : Data = leagueList[position]

        val listLogo = Constants.getLogo()

        for (i in leagueList){
            for (j in listLogo){
                Glide.with(holder.itemView)
                    .load(j.logoHome)
                    .into(holder.flagHome)

                Glide.with(holder.itemView)
                    .load(j.logoAway)
                    .into(holder.flagAway)
                resultLogo = com.example.footballmatchresults.business.models.leagueProfileLogo.Data(j.logoHome, j.logoAway)
            }
        }

        holder.nameHome.text = resultLeagueList.homeName
        holder.nameAway.text = resultLeagueList.awayName
        holder.resultHome.text = resultLeagueList.homeScore.toString()
        holder.resultAway.text = resultLeagueList.awayScore.toString()

        holder.itemView.setOnClickListener{
            leagueInfoListener.leagueInfo(resultLeagueList, resultLogo)
        }
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