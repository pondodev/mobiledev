package com.example.favefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // called when we wish to view the pizza
    fun viewPizza(view: View) {
        Log.i("clicked", "pizza")
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("message", "pizza")
        }
        startActivity(intent)
    }

    // called when we wish to view the nachos
    fun viewNachos(view: View) {
        Log.i("clicked", "nachos")
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("message", "nachos")
        }
        startActivity(intent)
    }

    // called when we wish to view the takis
    fun viewTakis(view: View) {
        Log.i("clicked", "takis")
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("message", "takis")
        }
        startActivity(intent)
    }

    // called when we wish to view the wreck
    fun viewTheWreck(view: View) {
        Log.i("clicked", "the wreck")
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("message", "the wreck")
        }
        startActivity(intent)
    }
}
