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
import com.example.footballmatchresults.business.models.league.Data
import com.example.footballmatchresults.presentation.adapter.listener.LeagueListener

class LeagueAdapter(val leagueListener: LeagueListener) : RecyclerView.Adapter<LeagueAdapter.LeagueAdapterViewHolder>() {

    private val leagueList =  mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)

        return LeagueAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: LeagueAdapterViewHolder, position: Int) {
        val resultLeagueList : Data = leagueList[position]

        Glide.with(holder.itemView).load(resultLeagueList.logo).into(holder.flag)
        holder.name.text = resultLeagueList.name
        holder.shortName.text = resultLeagueList.country

        holder.itemView.setOnClickListener {
            leagueListener.leagueList(resultLeagueList)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultLeagueList : List<Data>){
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