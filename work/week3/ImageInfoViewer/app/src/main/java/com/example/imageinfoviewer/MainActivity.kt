package com.example.imageinfoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ImageInfo
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var doggos = ArrayList<ImageInfo>()

    // construct the initial image info
    var borderCollie = ImageInfo(
        "border collie",
        "https://www.google.com/",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5.0f
    )
    var germanShepard = ImageInfo(
        "german shepard",
        "https://www.google.com/",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5.0f
    )
    var jackRussel = ImageInfo(
        "jack russel",
        "https://www.google.com/",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5.0f
    )
    var kelpie = ImageInfo(
        "kelpie",
        "https://www.google.com/",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5.0f
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // i know i can do this in a more memory efficient way but this is more readable
        try {
            doggos = intent.getParcelableArrayListExtra("doggos") // lmao so hacky
        } catch (e: IllegalStateException) {
            doggos.add(borderCollie)
            doggos.add(germanShepard)
            doggos.add(jackRussel)
            doggos.add(kelpie)
        }

        updateLabels()
    }

    private fun updateLabels() {
        // border collie
        var borderCollieInfo = doggos[0].name
        borderCollieInfo += "\n" + doggos[0].date

        // german shepard
        var germanShepardInfo = doggos[1].name
        germanShepardInfo += "\n" + doggos[1].date

        // jack russel
        var jackRusselInfo = doggos[2].name
        jackRusselInfo += "\n" + doggos[2].date

        // kelpie
        var kelpieInfo = doggos[3].name
        kelpieInfo += "\n" + doggos[3].date

        lblBorderCollie.text = borderCollieInfo
        lblGermanShepard.text = germanShepardInfo
        lblJackRussel.text = jackRusselInfo
        lblKelpie.text = kelpieInfo
    }

    fun viewBorderCollie(view: View) {
        val intent = Intent(this, ViewImageInfo::class.java).apply {
            putExtra("index", 0)
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }

    fun viewGermanShepard(view: View) {
        //
        val intent = Intent(this, ViewImageInfo::class.java).apply {
            putExtra("index", 1)
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }

    fun viewJackRussel(view: View) {
        val intent = Intent(this, ViewImageInfo::class.java).apply {
            putExtra("index", 2)
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }

    fun viewKelpie(view: View) {
        val intent = Intent(this, ViewImageInfo::class.java).apply {
            putExtra("index", 3)
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }
}
