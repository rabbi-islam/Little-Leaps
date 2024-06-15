package com.example.littleleaps

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.littleleaps.canvas.StrokeManager.clear
import com.example.littleleaps.canvas.StrokeManager.downloadLang
import com.example.littleleaps.canvas.StrokeManager.recognizer
import com.example.littleleaps.databinding.ActivityWritingBinding


class WritingActivity : AppCompatActivity() {
    private var toast: Toast? = null
    private var currentForEngCapIndex = 0
    private var currentForEngSmIndex = 0
    private var currentForBanVowelIndex = 0
    private var currentForBanConsIndex = 0
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
            downloadLang(this@WritingActivity,"en-IN")
            downloadLang(this@WritingActivity,"bn")
            btnCheck.setOnClickListener {
                recognizer(this@WritingActivity,previewText)
            }
            btnPencil.setOnClickListener {
                // Untuk mengganti dari false menjadi true
                isPencilIconClicked = !isPencilIconClicked

                if (isPencilIconClicked) {
                    btnPencil.setImageResource(R.drawable.ic_selected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_cards)

                    btnUndo.setImageResource(R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_card)
                    btnRectangle.setImageResource(R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_card)
                    btnEllipse.setImageResource(R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_card)
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)

                    drawPencil.visibility = View.VISIBLE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnPencil.setImageResource(R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnEraser.setOnClickListener {
                binding.drawPencil.clear()
                clear()
            }

            btnUndo.setOnClickListener {
                isArrowIconClicked = !isArrowIconClicked
                if (isArrowIconClicked) {
                    btnUndo.setImageResource(R.drawable.ic_selected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_cards)

                    btnPencil.setImageResource(R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_card)
                    btnRectangle.setImageResource(R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_card)
                    btnEllipse.setImageResource(R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_card)
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)

                    drawPencil.visibility = View.VISIBLE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnUndo.setImageResource(R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnRectangle.setOnClickListener {
                isRectangleIconClicked = !isRectangleIconClicked
                if (isRectangleIconClicked) {
                    btnRectangle.setImageResource(com.example.littleleaps.R.drawable.ic_selected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_cards)

                    btnPencil.setImageResource(R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_card)
                    btnUndo.setImageResource(R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_card)
                    btnEllipse.setImageResource(R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_card)
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)

                    drawRectangle.visibility = View.VISIBLE
                    drawPencil.visibility = View.GONE
                    drawLine.visibility = View.GONE
                    drawEllipse.visibility = View.GONE

                } else {
                    btnRectangle.setImageResource(R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnEllipse.setOnClickListener {
                isCircleIconClicked = !isCircleIconClicked

                if (isCircleIconClicked) {
                    btnEllipse.setImageResource(com.example.littleleaps.R.drawable.ic_selected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_cards)

                    btnPencil.setImageResource(R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_card)
                    btnUndo.setImageResource(R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_card)
                    btnRectangle.setImageResource(R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_card)
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)

                    drawEllipse.visibility = View.VISIBLE
                    drawPencil.visibility = View.GONE
                    drawLine.visibility = View.GONE
                    drawRectangle.visibility = View.GONE

                } else {
                    btnEllipse.setImageResource(R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_card)
                }
            }

            btnPallete.setOnClickListener {
                isPaletteIconClicked = !isPaletteIconClicked

                if (isPaletteIconClicked) {
                    colorPalate.visibility = View.VISIBLE

                    btnPallete.setImageResource(com.example.littleleaps.R.drawable.ic_selected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_cards)

                    btnPencil.setImageResource(R.drawable.ic_unselected_pencil)
                    btnPencil.setBackgroundResource(R.drawable.background_card)
                    btnUndo.setImageResource(R.drawable.ic_unselected_line)
                    btnUndo.setBackgroundResource(R.drawable.background_card)
                    btnRectangle.setImageResource(R.drawable.ic_unselected_rectangle)
                    btnRectangle.setBackgroundResource(R.drawable.background_card)
                    btnEllipse.setImageResource(R.drawable.ic_unselected_circle)
                    btnEllipse.setBackgroundResource(R.drawable.background_card)
                } else {
                    btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                    btnPallete.setBackgroundResource(R.drawable.background_card)
                    colorPalate.visibility = View.INVISIBLE
                }
            }

            btnBlue.setOnClickListener {
                paintBrush.color = resources.getColor(com.example.littleleaps.R.color.google_blue)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnRed.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_red)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnYellow.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_yellow)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnGreen.setOnClickListener {
                paintBrush.color = resources.getColor(R.color.google_green)
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

            btnBlack.setOnClickListener {
                paintBrush.color = Color.BLACK
                currentColor(paintBrush.color)
                colorPalate.visibility = View.INVISIBLE
                btnPallete.setImageResource(R.drawable.ic_unselected_palette)
                btnPallete.setBackgroundResource(R.drawable.background_card)
            }

//            btnRandom.setOnClickListener {
//                val engCapAlphabet = ('A'..'Z').toList() + ('a'..'z').toList() + (1..10).toList() + ('অ'..'ঔ').toList() + ('ক'..'ৎ').toList() +("১".."১০")
//                previewText.text  = engCapAlphabet.random().toString()
//            }
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

            btnSubmit.setOnClickListener {
                if (checkbox1.isChecked){
                    operationForCheckbox1()
                }else if (checkbox2.isChecked){
                    operationForCheckbox2()
                }else if(checkbox3.isChecked){
                    operationForCheckbox3()
                }else{
                    operationForCheckbox4()
                }

            }


        }
        binding.checkbox1.isChecked = true

    }



    private fun operationForCheckbox4() {

        val banConAlphabet = mutableListOf("ক","খ","গ","ঘ","ঙ","চ","ছ","জ","ঝ","ঞ","ট","ঠ","ড","ঢ",
                                            "ণ","ত","থ","দ","ধ","ন","প","ফ","ব","ভ","ম","য","র","ল",
                                            "শ","ষ","স","হ","ড়","ঢ়","য়","ৎ","ং")
        if (currentForBanConsIndex == banConAlphabet.size) {
            currentForBanConsIndex = 0 // Reset to the beginning of the list
        }
        // Set text element serially
        binding.previewText.text = banConAlphabet[currentForBanConsIndex]
        currentForBanConsIndex++

    }

    private fun operationForCheckbox3() {
        val banVowAlphabet = ('অ'..'ঔ').toList()
        if (currentForBanVowelIndex == banVowAlphabet.size) {
            currentForBanVowelIndex = 0
        }
        binding.previewText.text  = banVowAlphabet[currentForBanVowelIndex].toString()
        currentForBanVowelIndex++
    }

    private fun operationForCheckbox2() {
        val engSmAlphabet = mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        if (currentForEngSmIndex == engSmAlphabet.size) {
            currentForEngSmIndex = 0
        }
        binding.previewText.text = engSmAlphabet[currentForEngSmIndex].lowercase()
        currentForEngSmIndex++
    }

    private fun operationForCheckbox1() {
        val engCapAlphabet = mutableListOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        if (currentForEngCapIndex == engCapAlphabet.size) {
            currentForEngCapIndex = 0
        }
        binding.previewText.text = engCapAlphabet[currentForEngCapIndex]
        currentForEngCapIndex++
    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }







}
