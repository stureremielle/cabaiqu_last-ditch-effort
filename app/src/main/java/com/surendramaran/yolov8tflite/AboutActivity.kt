package com.surendramaran.yolov8tflite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // Initialize the back button and set its click listener
        val backButton: ImageButton = findViewById(R.id.back_btn)
        backButton.setOnClickListener {
            finish() // Close the AboutActivity and return to the previous activity
        }

        // Initialize the GitHub button and set its click listener
        val githubButton: ImageButton = findViewById(R.id.github_btn)
        githubButton.setOnClickListener {
            // Open the GitHub page in a browser
            val githubUrl = "https://github.com/stureremielle/cabaiqu_last-ditch-effort"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            startActivity(intent)
        }
    }
}