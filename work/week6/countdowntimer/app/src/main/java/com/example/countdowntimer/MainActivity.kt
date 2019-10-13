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
        timer().execute() // run our async task
    }

    // class that does our async stuff. must be internal to the class we're doing the task from
    internal inner class timer : AsyncTask<Void, Int, Int>() {

        // where we do our timer stuff
        override fun doInBackground(vararg p0: Void?): Int {
            try {
                for (i in 0..3) {
                    Thread.sleep(1000)
                    publishProgress(i) // can't modify the UI from this thread so we pass the value to the UI thread
                }
            } catch (ie: InterruptedException) {
                ie.printStackTrace()
            }

            return 0 // it wouldn't let me use a return type of void so we're doing this instead
        }

        // update the UI here
        override fun onProgressUpdate(vararg values: Int?) {
            display.text = values[0].toString()
            super.onProgressUpdate(*values)
        }
    }
}
