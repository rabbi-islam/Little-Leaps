package com.example.littleleaps.canvas

import android.content.Context
import android.media.MediaPlayer
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import com.example.littleleaps.R
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.DigitalInkRecognition
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier
import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions
import com.google.mlkit.vision.digitalink.Ink
import com.shashank.sony.fancytoastlib.FancyToast

object StrokeManager {

    private var model: DigitalInkRecognitionModel? = null
    private var inkBuilder = Ink.builder()
    private var strokeBuilder: Ink.Stroke.Builder? = null
    private var mp: MediaPlayer? = null
    private var correctWritingTimes = 0

    fun addNewTouchEvent(event: MotionEvent) {
        val x = event.x
        val y = event.y
        val t = System.currentTimeMillis()

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                strokeBuilder = Ink.Stroke.builder()
                strokeBuilder!!.addPoint(Ink.Point.create(x, y, t))
            }

            MotionEvent.ACTION_MOVE -> strokeBuilder!!.addPoint(Ink.Point.create(x, y, t))

            MotionEvent.ACTION_UP -> {
                strokeBuilder!!.addPoint(Ink.Point.create(x, y, t))
                inkBuilder.addStroke(strokeBuilder!!.build())
                strokeBuilder = null
            }
        }
    }

    fun downloadLang(context: Context,languageName:String) {
        var modelIdentifier: DigitalInkRecognitionModelIdentifier? = null

        try {
            modelIdentifier = DigitalInkRecognitionModelIdentifier.fromLanguageTag(languageName)
        } catch (e: Exception) {

        }

        model = DigitalInkRecognitionModel.builder(modelIdentifier!!).build()
        val remoteModelManager = RemoteModelManager.getInstance()
        remoteModelManager.download(model!!, DownloadConditions.Builder().build())
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    fun recognizer(context: Context, textView: TextView) {
        val recognizer = DigitalInkRecognition.getClient(
            DigitalInkRecognizerOptions.builder(
                model!!
            ).build()
        )
        val ink = inkBuilder.build()
        recognizer.recognize(ink)
            .addOnSuccessListener {
                val gettext = it.candidates[0].text
                if (gettext == textView.text) {
                    if (mp == null){
                        val mp = MediaPlayer.create(context, R.raw.correct)
                        mp.start()
                    }
                    correctWritingTimes++
                    FancyToast.makeText(context, "Matched :)$correctWritingTimes", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show()
                } else {
                    if (mp == null){
                        val mp = MediaPlayer.create(context, R.raw.wrong)
                        mp.start()
                    }
                    correctWritingTimes = 0
                    FancyToast.makeText(context, "Not Matched :)", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show()
                }
            }
            .addOnFailureListener {

            }
    }


     fun showingShoutOutMessage():String =  when(correctWritingTimes) {
        2 -> "good"
        3 -> "Very Good"
        5 -> "Excellent"
        else -> ""
    }


    fun clear() {
        inkBuilder = Ink.builder()
    }
}
