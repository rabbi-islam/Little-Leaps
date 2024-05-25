package com.example.littleleaps

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.example.littleleaps.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val binding:ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.splashScreen.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

        binding.hamburger.setOnClickListener {
           showMenu(it)
        }
    }

    private fun showMenu(view:View) {
        val popupMenu = PopupMenu(this,view)
        popupMenu.menuInflater.inflate(R.menu.splash_screen_itemr,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Handle menu item 1 click
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.login -> {
                    startActivity(Intent(this,LoginActivity::class.java))
                    true
                }
                else -> false
            }
        }
        popupMenu.show()

    }


}