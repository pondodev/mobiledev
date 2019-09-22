package com.example.countdowntimer

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        try {
            for (i in 0..3) {
                Thread.sleep(1000)
                display.text = i.toString()
            }
        } catch (ie: InterruptedException) {
            ie.printStackTrace()
        }
    }
}
