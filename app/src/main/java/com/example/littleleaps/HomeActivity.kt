package com.example.littleleaps

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.models.SlideModel
import com.example.littleleaps.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    private val binding:HomeActivityBinding by lazy {
        HomeActivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addingImageToSlider()
        navigateToActivity()
    }

    private fun navigateToActivity() {

        binding.dailyLesson.setOnClickListener {
            startActivity(Intent(this,DailyLessonActivity::class.java))
        }

        binding.reading.setOnClickListener {
            startActivity(Intent(this,ReadingActivity::class.java))
        }

        binding.writing.setOnClickListener {
            startActivity(Intent(this,WritingActivity::class.java))
        }

        binding.quiz.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
        }
    }

    private fun addingImageToSlider() {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.sliderone))
        imageList.add(SlideModel(R.drawable.slidertwo))
        imageList.add(SlideModel(R.drawable.sliderthree))

        binding.imageSlider.setImageList(imageList)

        binding.circularProgressBar.apply {
            setProgressWithAnimation(60f,1000)
            progressMax = 100f
            progressBarWidth = 15f
            roundBorder = true
            backgroundProgressBarWidth = 35f
            progressBarColor = Color.parseColor("#43C6C8")
            backgroundProgressBarColor = Color.parseColor("#DFDFDF")
        }
    }

}