package au.edu.swin.sdmd.sunrisekotlin.calc

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import au.edu.swin.sdmd.sunrisekotlin.R
import kotlinx.android.synthetic.main.activity_add_date.*
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class AddDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_date)
    }

    fun save(_v: View) {
        val name = txtName.text.toString()
        val lat = txtLatitude.text.toString()
        val lon = txtLongitude.text.toString()
        val tz = txtTimezone.text.toString()
        val data = name + "," + lat + "," + lon + "," + tz + "\n"

        val file = File(filesDir, "locations.txt")
        val fos = FileOutputStream(file, true)
        fos.write(data.toByteArray())
        finish()
    }
}
