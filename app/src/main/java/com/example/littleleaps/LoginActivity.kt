package com.example.littleleaps

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.littleleaps.databinding.ActivityLoginBinding
import com.example.littleleaps.databinding.ActivitySplashBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.signInButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.progres_dailog)
        dialog.setCancelable(false)
        dialog.show()
        val email = binding.emailEdittext.text.trim().toString()
        val pass = binding.passwordEdittext.text.trim().toString()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, TeacherDashboardActivity::class.java))
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT)
                                .show()
                            dialog.dismiss()
                        } else
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                )

        }else
            Toast.makeText(this, "Field Must Not Be Empty", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

    }
}