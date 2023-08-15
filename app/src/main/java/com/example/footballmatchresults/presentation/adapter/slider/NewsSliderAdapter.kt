package com.example.footballmatchresults.presentation.adapter.slider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballmatchresults.R
import com.example.footballmatchresults.business.models.slide.NewsModel

class NewsSliderAdapter() : RecyclerView.Adapter<NewsSliderAdapter.NewsSliderViewHolder>() {

    private val newsList =  mutableListOf<NewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_news, parent, false)

        return NewsSliderViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsSliderViewHolder, position: Int) {
        val resultNewsList = newsList[position]

        holder.titleNews.text = resultNewsList.title
        holder.dateNews.text = resultNewsList.date
        holder.icon.setImageResource(R.drawable.def)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(resultList : List<NewsModel>){
        this.newsList.clear()
        this.newsList.addAll(resultList)
        notifyDataSetChanged()
    }

    class NewsSliderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val titleNews : TextView = view.findViewById(R.id.tv_title_news)
        val dateNews : TextView = view.findViewById(R.id.tv_date_news)
        val icon : ImageView = view.findViewById(R.id.icon_def)
    }

}