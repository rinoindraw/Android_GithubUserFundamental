package com.rinoindraw.githubyangke3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.rinoindraw.githubyangke3.databinding.ActivityMainBinding
import com.rinoindraw.githubyangke3.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val imageLogo = findViewById<ImageView>(R.id.logo_titlebar)
        imageLogo.alpha = 0f
        imageLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            val intentSplash = Intent(this, MainActivity::class.java)
            startActivity(intentSplash)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}