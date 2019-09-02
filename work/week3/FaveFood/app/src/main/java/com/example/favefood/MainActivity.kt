package com.example.favefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // called when we wish to view the pizza
    fun viewPizza(view: View) {
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("description", getString(R.string.pizza))
            putExtra("id", R.drawable.pizza)
        }
        startActivity(intent)
    }

    // called when we wish to view the nachos
    fun viewNachos(view: View) {
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("description", getString(R.string.nachos))
            putExtra("id", R.drawable.nachos)
        }
        startActivity(intent)
    }

    // called when we wish to view the takis
    fun viewTakis(view: View) {
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("description", getString(R.string.takis))
            putExtra("id", R.drawable.takis)
        }
        startActivity(intent)
    }

    // called when we wish to view the wreck
    fun viewTheWreck(view: View) {
        val intent = Intent(this, ImageDisplay::class.java).apply {
            putExtra("description", getString(R.string.thewreck))
            putExtra("id", R.drawable.thewreck)
        }
        startActivity(intent)
    }
}
