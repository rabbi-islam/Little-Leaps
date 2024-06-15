package com.example.littleleaps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.littleleaps.apis.ApiService
import com.example.littleleaps.apis.RetrofitInstance
import com.example.littleleaps.canvas.StrokeManager
import com.example.littleleaps.databinding.ActivityReadingBinding
import com.example.littleleaps.model.TextResponseForReadingAlphabetApi
import com.github.squti.androidwaverecorder.WaveRecorder
import com.shashank.sony.fancytoastlib.FancyToast
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ReadingActivity : AppCompatActivity() {

    private var currentForEngCapIndex = 0
    private val REQUEST_CODE = 200
    private val engCapAlphabet = mutableListOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    )

    private val wordList = mutableListOf(
        "Apple", "Ball", "Cat", "Dog", "Egg", "Flag", "Gun", "Hut"
    )
    private val binding: ActivityReadingBinding by lazy {
        ActivityReadingBinding.inflate(layoutInflater)
    }
    private lateinit var waveRecorder: WaveRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO
    )
    private var permissionGranted = false
    private var mp: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        permissionGranted = ActivityCompat.checkSelfPermission(
            this,
            permissions[0]
        ) == PackageManager.PERMISSION_GRANTED
        if (!permissionGranted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }


        binding.nextImageView.setOnClickListener {
            setTextForNextImageviewButton()
        }
        binding.prevImageView.setOnClickListener {
            setTextForPreviousImageviewButton()
        }
        binding.speakBtn.setOnClickListener {
            speakingButtonAnimationWithTask()
        }

        binding.playingbutton.setOnClickListener {
            playRecording()
        }

//        binding.testingbutton.setOnClickListener {
//            sendAudioFile()
//        }

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }


    }

    private fun setTextForNextImageviewButton() {
        if (currentForEngCapIndex >= engCapAlphabet.size) {
            currentForEngCapIndex = 0
        }

        binding.alphabetTV.text = engCapAlphabet[currentForEngCapIndex]
        currentForEngCapIndex++
    }


    private fun setTextForPreviousImageviewButton() {
        // Move to the previous alphabet letter
        currentForEngCapIndex = if (currentForEngCapIndex > 0) {
            currentForEngCapIndex - 1
        } else {
            engCapAlphabet.size - 1
        }
        binding.alphabetTV.text = engCapAlphabet[currentForEngCapIndex]
    }

    private fun speakingButtonAnimationWithTask() {
        val image = BitmapFactory.decodeResource(this.resources, R.drawable.check)
        binding.speakBtn.apply {
            startAnimation()
            startRecording()
            Handler(Looper.getMainLooper()).postDelayed({
                waveRecorder.stopRecording()
                doneLoadingAnimation(Color.YELLOW, image)
                Handler(Looper.getMainLooper()).postDelayed({
                    revertAnimation()
                    stopAnimation()
                    sendAudioFile()
                }, 1800)
            }, 1500)

        }
    }

    private fun sendAudioFile(){
        val filePath = "${filesDir.absolutePath}/myAudio.wav"
        val audioFile = File(filePath)
        if (!audioFile.exists()) {
            // Handle the case where the audio file is not found
            println("Audio file not found: $filePath")
            return
        }


        val requestFile = audioFile.asRequestBody("audio/wav".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)
        val instance = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val call = instance.sendAudio(body)
        call.enqueue(object : Callback<TextResponseForReadingAlphabetApi?> {
            override fun onResponse(
                call: Call<TextResponseForReadingAlphabetApi?>,
                response: Response<TextResponseForReadingAlphabetApi?>
            ) {
                if (response.isSuccessful){
                    val textResponse = response.body()?.transcription

                    val text = textResponse?.filter {
                        it.isLetterOrDigit()
                    }?.lowercase()

                    binding.previewtxt.text = text
                    val gettext = replacingText(text!!)
                    matchingTextWithShowingMusicAndToast(gettext.uppercase())
                    Toast.makeText(this@ReadingActivity, gettext, Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(this@ReadingActivity, "failed", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<TextResponseForReadingAlphabetApi?>, t: Throwable) {
                Toast.makeText(this@ReadingActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun playRecording() {
        // Assuming you have already recorded the audio and saved it as "myAudio.wav" in your app's internal storage
        val filePath = "${filesDir.absolutePath}/myAudio.wav"

         mediaPlayer = MediaPlayer().apply {
            setDataSource(filePath)
            prepare() // You might want to call this on a background thread to avoid blocking the UI thread
            start()
        }
    }

    private fun startRecording() {
        val filePath = "${this.filesDir.absolutePath}/myAudio.wav"
        waveRecorder = WaveRecorder(filePath)
        waveRecorder.startRecording()
    }

    fun replacingText(inputText: String): String {

        val replacements = mapOf(
            "a" to "A",
            "hey" to "A",
            "okay" to "A",
            "hmm" to "A",
            "me" to "B",
            "be" to "B",
            "b" to "B",
            "see" to "C",
            "c" to "C",
            "d" to "D",
            "de" to "D",
            "e" to "E",
            "eee" to "E",
            "eeee" to "E",
            "f" to "F",
            "if" to "F",
            "g" to "G",
            "gg" to "G",
            "ace" to "H",
        )

        return replacements[inputText.lowercase()]?: "doesn't match"
    }

    private fun matchingTextWithShowingMusicAndToast(text:String){
        if (text == binding.alphabetTV.text.toString()) {
            if (mp == null){
                val mp = MediaPlayer.create(this, R.raw.correct)
                mp.start()
                mp.setOnCompletionListener {
                    mp.stop()
                    mp.release()
                }
            }
            showImageToast(this,R.drawable.tick)
        } else {
            if (mp == null){
                val mp = MediaPlayer.create(this, R.raw.wrong)
                mp.start()
                mp.setOnCompletionListener {
                    mp.stop()
                    mp.release()
                }
            }
            showImageToast(this,R.drawable.fail)
        }
    }

    private fun showImageToast(context: Context, imageResId: Int) {
        val imageView = ImageView(applicationContext)
        imageView.setImageResource(imageResId)

        val toast = Toast(context)
        toast.view = imageView
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.speakBtn.dispose()
    }
}
