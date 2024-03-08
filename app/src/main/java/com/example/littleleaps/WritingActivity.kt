package com.example.littleleaps

import android.R
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.littleleaps.canvas.DrawPencil
import com.example.littleleaps.databinding.ActivityWritingBinding


class WritingActivity : AppCompatActivity() {
    private val binding: ActivityWritingBinding by lazy {
        ActivityWritingBinding.inflate(layoutInflater)
    }

        private var isPencilIconClicked = false
        private var isArrowIconClicked = false
        private var isRectangleIconClicked = false
        private var isCircleIconClicked = false
        private var isPaletteIconClicked = false

        companion object {
            var path = Path()
            var paintBrush = Paint()
            var colorList = ArrayList<Int>()
            var currentBrush = Color.BLACK
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            supportActionBar?.hide()

            binding.apply {

                btnPencil.setOnClickListener {

                    isPencilIconClicked = !isPencilIconClicked

                    if (isPencilIconClicked) {

                        btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_selected_pencil)
                        btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                        btnArrow.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                        btnArrow.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnErase.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                        btnErase.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                        btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                        btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    }
                    else {
                        btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                        btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    }


                }




                btnErase.setOnClickListener {
                    isRectangleIconClicked = !isRectangleIconClicked
                    if (isRectangleIconClicked) {
                        drawPencil.erase()


                    } else {
                        btnErase.setImageResource(com.example.littleleaps.R.drawable.eraser)
                        btnErase.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    }
                }


                btnPallete.setOnClickListener {
                    isPaletteIconClicked = !isPaletteIconClicked

                    if (isPaletteIconClicked) {
                        colorPalate.visibility = View.VISIBLE

                        btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_selected_palette)
                        btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                        btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                        btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnArrow.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                        btnArrow.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnErase.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                        btnErase.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                        btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    } else {
                        btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                        btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                        colorPalate.visibility = View.INVISIBLE
                    }
                }

                btnBlue.setOnClickListener {
                    paintBrush.color = resources.getColor(com.example.littleleaps.R.color.google_blue)
                    currentColor(paintBrush.color)
                    colorPalate.visibility = View.INVISIBLE
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }

                btnRed.setOnClickListener {
                    paintBrush.color = resources.getColor(com.example.littleleaps.R.color.google_red)
                    currentColor(paintBrush.color)
                    colorPalate.visibility = View.INVISIBLE
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }

                btnYellow.setOnClickListener {
                    paintBrush.color = resources.getColor(com.example.littleleaps.R.color.google_yellow)
                    currentColor(paintBrush.color)
                    colorPalate.visibility = View.INVISIBLE
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }

                btnGreen.setOnClickListener {
                    paintBrush.color = resources.getColor(com.example.littleleaps.R.color.google_green)
                    currentColor(paintBrush.color)
                    colorPalate.visibility = View.INVISIBLE
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }

                btnBlack.setOnClickListener {
                    paintBrush.color = Color.BLACK
                    currentColor(paintBrush.color)
                    colorPalate.visibility = View.INVISIBLE
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }
            }
        }

        private fun currentColor(color: Int) {
            currentBrush = color
            path = Path()
        }







    }
