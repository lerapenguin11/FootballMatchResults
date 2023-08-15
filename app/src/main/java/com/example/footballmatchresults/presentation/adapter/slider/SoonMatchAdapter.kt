package com.example.footballmatchresults.presentation.adapter.slider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.models.slide.SoonMatchModel
import com.example.footballmatchresults.presentation.adapter.listener.SoonMatchListener

class SoonMatchAdapter(val soonMatchListener: SoonMatchListener) : RecyclerView.Adapter<SoonMatchAdapter.SoonMatchViewHolder>() {

    private val matchList =  mutableListOf<SoonMatchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoonMatchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_intuition_points, parent, false)

        return SoonMatchViewHolder(view)
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: SoonMatchViewHolder, position: Int) {
        val resultMatch = matchList[position]

        holder.date.text = resultMatch.date
        holder.nameHome.text = resultMatch.nameHome
        holder.nameAway.text = resultMatch.nameAway
        holder.resultHome.text = resultMatch.resultHome
        holder.resultAway.text = resultMatch.resultAway

        holder.itemView.setOnClickListener {
            soonMatchListener.soonMatchList(resultMatch)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultList : List<SoonMatchModel>){
        this.matchList.clear()
        this.matchList.addAll(resultList)
        notifyDataSetChanged()
    }

    class SoonMatchViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val date : TextView = view.findViewById(R.id.tv_date_in_point)
        val nameHome : TextView = view.findViewById(R.id.tv_name_home_point)
        val nameAway : TextView = view.findViewById(R.id.tv_name_home_point)
        val resultHome : TextView = view.findViewById(R.id.tv_result_home_point)
        val resultAway : TextView = view.findViewById(R.id.tv_result_away_point)
    }
}