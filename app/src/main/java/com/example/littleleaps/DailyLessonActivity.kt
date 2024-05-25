package com.example.littleleaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.littleleaps.adapter.CardViewAdapter
import com.example.littleleaps.model.DailyLessonCardModel

class DailyLessonActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataAdapter: CardViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_lesson)

        recyclerView = findViewById(R.id.cardRecycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val list: List<DailyLessonCardModel> = listOf(
            DailyLessonCardModel(R.drawable.alphabet),
            DailyLessonCardModel(R.drawable.numbers),
            DailyLessonCardModel(R.drawable.bangla_number),
            DailyLessonCardModel(R.drawable.numbers),
            DailyLessonCardModel(R.drawable.alphabet),
            DailyLessonCardModel(R.drawable.bangla_number)
        )
        dataAdapter = CardViewAdapter(list)
        //recyclerView.addItemDecoration(ItemDecoration(10))
        val screenWidth = resources.displayMetrics.widthPixels
        val centerX = resources.displayMetrics.widthPixels / 2
        val offset = (screenWidth / 2) - (850 / 2)
        recyclerView.setPadding(offset, 0, offset, 0)
        recyclerView.clipToPadding = false
        recyclerView.adapter = dataAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Loop through visible items to calculate scale adjustment
                for (i in 0 until recyclerView.childCount) {
                    val child = recyclerView.getChildAt(i)
                    val position = recyclerView.getChildAdapterPosition(child)
                    val childCenterX = (child.left + child.right) / 2
                    val distanceFromCenter = Math.abs(centerX - childCenterX).toFloat()

                    // Calculate scale adjustment based on distance from center
                    val scaleAdjustment =
                        1 - distanceFromCenter / centerX // Adjust scale factor as needed

                    // Apply scale adjustment to the item
                    child.scaleX = scaleAdjustment
                    child.scaleY = scaleAdjustment
                }
            }


        })

        dataAdapter.setOnItemClickListener(object : CardViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@DailyLessonActivity, "Clicked on item $position", Toast.LENGTH_SHORT).show()
            }
        })


    }


}
