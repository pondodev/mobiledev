package com.example.favefood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgPizza.setOnClickListener {
            // do stuff
        }

        imgNachos.setOnClickListener {
            // do stuff
        }

        imgTakis.setOnClickListener {
            // do stuff
        }

        imgTheWreck.setOnClickListener {
            // do stuff
        }
    }
}
