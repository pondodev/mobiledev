package au.edu.swin.sdmd.sunrisekotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import au.edu.swin.sdmd.sunrisekotlin.calc.AstronomicalCalendar
import au.edu.swin.sdmd.sunrisekotlin.calc.GeoLocation
import kotlinx.android.synthetic.main.fragment_info_view.*
import java.text.SimpleDateFormat
import java.util.*

class InfoView : Fragment() {

    lateinit var city: String
    var lat: Double = 0.0
    var lon: Double = 0.0
    lateinit var sunrise: String
    lateinit var sunset: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the info for the city
        city = arguments?.get("city") as String
        lat = arguments?.get("lat") as Double
        lon = arguments?.get("lon") as Double
    }

    override fun onResume() {
        super.onResume()

        initializeUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_view, container, false)
    }

    private fun initializeUI() {
        val dp = datePicker
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        dp.init(year, month, day, dateChangeHandler) // setup initial values and reg. handler
        updateTime(year, month, day)
        locationTV.text = city
    }

    private fun updateTime(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val tz = TimeZone.getDefault()
        val geolocation = GeoLocation(city, lat, lon, tz)
        val ac = AstronomicalCalendar(geolocation)
        ac.getCalendar().set(year, monthOfYear, dayOfMonth)
        val srise = ac.getSunrise()
        val sset = ac.getSunset()

        val sdf = SimpleDateFormat("HH:mm")

        val sunriseTV = sunriseTimeTV
        val sunsetTV = sunsetTimeTV
        Log.d("SUNRISE Unformatted", srise.toString())

        sunrise = sdf.format(srise)
        sunset = sdf.format(sset)
        sunriseTV.text = sunrise
        sunsetTV.text = sunset
    }

    internal var dateChangeHandler: DatePicker.OnDateChangedListener =
        DatePicker.OnDateChangedListener { dp, year, monthOfYear, dayOfMonth ->
            updateTime(
                year,
                monthOfYear,
                dayOfMonth
            )
        }

}
