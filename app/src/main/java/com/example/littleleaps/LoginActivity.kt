package com.example.littleleaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.littleleaps.databinding.ActivityLoginBinding
import com.example.littleleaps.databinding.ActivitySplashBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

        binding.signInButton.setOnClickListener {
            startActivity(Intent(this,TeacherDashboardActivity::class.java))
        }
    }
}