package com.example.footballmatchresults.presantation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.models.league.LeagueModel

class LeagueAdapter() : RecyclerView.Adapter<LeagueAdapter.LeagueAdapterViewHolder>() {

    private val leagueList =  mutableListOf<LeagueModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)

        return LeagueAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: LeagueAdapterViewHolder, position: Int) {
        val resultLeagueList : LeagueModel = leagueList[position]

        Glide.with(holder.itemView).load(resultLeagueList.data.leagueInfo.logo).into(holder.flag)
        holder.name.text = resultLeagueList.data.leagueInfo.name
        holder.shortName.text = resultLeagueList.data.leagueInfo.shortName
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultLeagueList : List<LeagueModel>){
        this.leagueList.clear()
        this.leagueList.addAll(resultLeagueList)
        notifyDataSetChanged()
    }

    class LeagueAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val flag : ImageView = view.findViewById(R.id.iv_flag)
        val name : TextView = view.findViewById(R.id.tv_name)
        val shortName : TextView = view.findViewById(R.id.tv_short_name)
    }
}