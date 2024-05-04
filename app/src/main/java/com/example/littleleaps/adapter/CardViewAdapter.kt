package com.example.littleleaps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.littleleaps.R
import com.example.littleleaps.model.DailyLessonCardModel

class CardViewAdapter(private val cardList: List<DailyLessonCardModel>) : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.lessonListImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_lesson_list, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.imageView.setImageResource(cardList[position].image)
    }

    override fun getItemCount(): Int = cardList.size
}