package com.example.littleleaps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.littleleaps.R
import com.example.littleleaps.model.DailyLessonCardModel


class CardViewAdapter(private val cardList: List<DailyLessonCardModel>) : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {

    private lateinit var mListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun  setOnItemClickListener(listener:OnItemClickListener){
          mListener = listener
    }
    class CardViewHolder(itemView: View,listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.lessonListImage)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_lesson_list, parent, false)
        return CardViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.imageView.setImageResource(cardList[position].image)
    }

    override fun getItemCount(): Int = cardList.size

}