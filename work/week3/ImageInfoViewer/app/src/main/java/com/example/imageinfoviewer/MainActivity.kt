package com.example.imageinfoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ImageInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var doggos = ArrayList<ImageInfo>()

    // construct the initial image info
    var borderCollie = ImageInfo(
        "border collie",
        "google.com",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5
    )
    var germanShepard = ImageInfo(
        "german shepard",
        "google.com",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5
    )
    var jackRussel = ImageInfo(
        "jack russel",
        "google.com",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5
    )
    var kelpie = ImageInfo(
        "kelpie",
        "google.com",
        "dog, bork, good boye",
        "idk",
        false,
        "yes@yes.com",
        5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // i know i can do this in a more memory efficient way but this is more readable
        doggos.add(borderCollie)
        doggos.add(germanShepard)
        doggos.add(jackRussel)
        doggos.add(kelpie)

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
}
