package com.example.littleleaps

import android.R
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var isEraserIconClicked = false

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
                // Untuk mengganti dari false menjadi true
                isPencilIconClicked = !isPencilIconClicked

                if (isPencilIconClicked) { // ini untuk mengecek apakah isPencilIconClicked sudah true valuenya
                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_selected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    drawPencil.visibility = View.VISIBLE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }
            }

            btnEraser.setOnClickListener {
                isEraserIconClicked = !isEraserIconClicked

                if (isEraserIconClicked) {
                    btnEraser.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    drawPencil.erase()

                    drawPencil.visibility = View.VISIBLE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }
            }

            btnUndo.setOnClickListener {
                isArrowIconClicked = !isArrowIconClicked
                if (isArrowIconClicked) {
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_selected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    drawPencil.undo()
                    drawPencil.visibility = View.VISIBLE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }
            }

            btnRectangle.setOnClickListener {
                isRectangleIconClicked = !isRectangleIconClicked
                if (isRectangleIconClicked) {
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_selected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    drawRectangle.visibility = View.VISIBLE
                    drawPencil.visibility = View.GONE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE

                } else {
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                }
            }

            btnEllipse.setOnClickListener {
                isCircleIconClicked = !isCircleIconClicked

                if (isCircleIconClicked) {
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_selected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_cards)

                    btnPencil.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)

                    drawEllipse.visibility = View.VISIBLE
                    drawPencil.visibility = View.GONE
                    drawLine.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
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
                    btnUndo.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(com.example.littleleaps.R.drawable.background_card)
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

            btnRandom.setOnClickListener {
                val engCapAlphabet = mutableListOf<String>("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
                previewText.text  = engCapAlphabet.random()
            }

            val checkboxes = listOf(checkbox1, checkbox2, checkbox3, checkbox4)

            for (checkbox in checkboxes) {
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // Uncheck all other checkboxes
                        for (otherCheckbox in checkboxes) {
                            if (otherCheckbox != checkbox) {
                                otherCheckbox.isChecked = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }







}
