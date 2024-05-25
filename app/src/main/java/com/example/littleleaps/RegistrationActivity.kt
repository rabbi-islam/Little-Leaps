package com.example.littleleaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.littleleaps.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private val binding:ActivityRegistrationBinding by lazy {
        ActivityRegistrationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signInTv.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }
}