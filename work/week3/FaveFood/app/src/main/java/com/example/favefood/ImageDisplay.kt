package com.example.favefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_image_display.*

class ImageDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_display)

        // get the content from the intent that started this activity
        val message = intent.getStringExtra("message")
        lblTest.text = message
    }

    // called when we wish to go back to the main activity
    fun back(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
