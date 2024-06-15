package com.example.littleleaps

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.littleleaps.databinding.ActivityRegistrationBinding
import com.example.littleleaps.model.TeacherInfo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: DatabaseReference

    private val binding: ActivityRegistrationBinding by lazy {
        ActivityRegistrationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Teachers") // .child(auth.currentUser?.uid.toString()

        binding.signInTv.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.signUpButton.setOnClickListener {
            register()
        }


    }


    private fun register() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.progres_dailog)
        dialog.setCancelable(false)
        if (dialog.window != null)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog.show()

        val name = binding.nameEdittext.text.trim().toString()
        val email = binding.emailEdittext.text.trim().toString()
        val pass = binding.passwordEdittext.text.trim().toString()
        if (name.isNotEmpty() && email.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, email)
                .addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT)
                                .show()
                            onSaveTask(TeacherInfo(name,email))
                            startActivity(Intent(this,TeacherDashboardActivity::class.java))
                            dialog.dismiss()
                        } else
                            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT)
                                .show()
                        dialog.dismiss()
                    }
                )
        } else {
            Toast.makeText(this, "field must not be empty", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    private fun onSaveTask(teacherInfo:TeacherInfo) {
        firebaseDatabase.push().setValue(teacherInfo).addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show()
                }
            }
        )

    }
}