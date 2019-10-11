package au.edu.swin.sdmd.sunrisekotlin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import au.edu.swin.sdmd.sunrisekotlin.calc.AddDate
import au.edu.swin.sdmd.sunrisekotlin.calc.AstronomicalCalendar
import au.edu.swin.sdmd.sunrisekotlin.calc.GeoLocation
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // check if the file already exists (don't want to overwrite it!)
        if (!getFileStreamPath("locations.txt").exists()) {
            writeToInternaleStorage()
        }
        initializeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivityForResult(intentFor<AddDate>(), 1)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        populateDropDown(year, month, day)
    }

    private fun initializeUI() {
        val dp = findViewById<DatePicker>(R.id.datePicker)
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        dp.init(year, month, day, dateChangeHandler) // setup initial values and reg. handler
        updateTime(year, month, day)
        populateDropDown(year, month, day)
    }

    private fun populateDropDown(year: Int, month: Int, day: Int) {
        // reading in the file
        var options = ArrayList<String>()
        val file = openFileInput("locations.txt")
        val reader = InputStreamReader(file)
        val buffReader = BufferedReader(reader)

        // populate the drop down menu
        var line = buffReader.readLine()
        while (line != null) {
            options.add(line.split(",")[0])

            line = buffReader.readLine()
        }
        location_selection.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        location_selection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // is this a really bad way to do things? yes. i should have created an object for
                // all of the locations that i could then iterate through. you know what though? i'm
                // not being marked for efficiency and good code so it's stayin like this, if only
                // because it's easier as i bodge this all together
                val name = location_selection.selectedItem as String
                val lat: Double
                val lon: Double
                val timezone: String

                // read file for our location
                val file = openFileInput("locations.txt")
                val reader = InputStreamReader(file)
                val buffReader = BufferedReader(reader)
                var line = buffReader.readLine()
                while (line != null) {
                    if (line.split(",")[0] == name)
                        break // will break if we have a match
                    line = buffReader.readLine()
                }

                lat = line.split(",")[1].toDouble()
                lon = line.split(",")[2].toDouble()
                timezone = line.split(",")[3]

                updateLocation(year, month, day, name, lat, lon, timezone)
            }
        }
    }

    private fun updateLocation(year: Int, monthOfYear: Int, dayOfMonth: Int, name: String, lat: Double, lon: Double, timezone: String) {
        val tz = TimeZone.getTimeZone(timezone)
        val geolocation = GeoLocation(name, lat, lon, tz)
        val ac = AstronomicalCalendar(geolocation)
        ac.getCalendar().set(year, monthOfYear, dayOfMonth)
        val srise = ac.getSunrise()
        val sset = ac.getSunset()

        val sdf = SimpleDateFormat("HH:mm")

        val sunriseTV = findViewById<TextView>(R.id.sunriseTimeTV)
        val sunsetTV = findViewById<TextView>(R.id.sunsetTimeTV)
        Log.d("SUNRISE Unformatted", srise.toString())

        sunriseTV.setText(sdf.format(srise))
        sunsetTV.setText(sdf.format(sset))
        locationTV.text = name
    }

    private fun updateTime(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val tz = TimeZone.getDefault()
        val geolocation = GeoLocation("Melbourne", -37.50, 145.01, tz)
        val ac = AstronomicalCalendar(geolocation)
        ac.getCalendar().set(year, monthOfYear, dayOfMonth)
        val srise = ac.getSunrise()
        val sset = ac.getSunset()

        val sdf = SimpleDateFormat("HH:mm")

        val sunriseTV = findViewById<TextView>(R.id.sunriseTimeTV)
        val sunsetTV = findViewById<TextView>(R.id.sunsetTimeTV)
        Log.d("SUNRISE Unformatted", srise.toString())

        sunriseTV.setText(sdf.format(srise))
        sunsetTV.setText(sdf.format(sset))
    }

    internal var dateChangeHandler: DatePicker.OnDateChangedListener =
        DatePicker.OnDateChangedListener { dp, year, monthOfYear, dayOfMonth ->
            updateTime(
                year,
                monthOfYear,
                dayOfMonth
            )
        }
    private fun writeToInternaleStorage() {
        val file = resources.openRawResource(R.raw.au_locations)
        val fos = openFileOutput("locations.txt", Context.MODE_PRIVATE)

        val reader = InputStreamReader(file)
        val buffReader = BufferedReader(reader)
        var line = buffReader.readLine()

        while (line != null) {
            fos.write((line + "\n").toByteArray())
            line = buffReader.readLine()
        }
    }
}
