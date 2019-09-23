package au.edu.swin.sdmd.sunrisekotlin

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_generate_table.*
import org.jetbrains.anko.longToast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class GenerateTable : AppCompatActivity() {

    private lateinit var city: String
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private lateinit var startDate: Date
    private lateinit var endDate: Date

    private lateinit var dateEntryAdapter: DateEntryRecyclerAdapter

    var sdf = SimpleDateFormat("dd MMM YYYY", Locale.UK)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_table)

        // get variables sent with the intent
        city = intent.getStringExtra("city")
        lat = intent.getDoubleExtra("lat", 0.0)
        lon = intent.getDoubleExtra("lon", 0.0)

        txtLocation.text = city

        // defaults for the dates we display
        startDate = Calendar.getInstance().time
        endDate = Calendar.getInstance().time
        txtStartDate.text = sdf.format(startDate)
        txtEndDate.text = sdf.format(endDate)

        // recycler view stuffs
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GenerateTable)
            dateEntryAdapter = DateEntryRecyclerAdapter()
            adapter = dateEntryAdapter
        }
    }

    // called when we open up date picker to choose start date
    fun setStartDate(_v: View) {
        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            // set the start date and it's display
            startDate = selectedDate.time
            txtStartDate.text = sdf.format(selectedDate.time)
        },
            now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
    }

    // called when we open up date picker to choose end date
    fun setEndDate(_v: View) {
        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            // set the end date and it's display
            endDate = selectedDate.time
            txtEndDate.text = sdf.format(endDate)
        },
            now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
    }

    fun generate(_v: View) {
        // check if the start date is after the end date
        if (startDate.time > endDate.time) {
            longToast("Please ensure the start date is not after the end date")
            return
        }

        // we can now show all the dates
        var data = ArrayList<DateEntry>()

        // iterate through each day
        // Date.time returns the unix timestamp in milliseconds
        // there's 86400000 milliseconds in a day, so we increment by that each time
        for (i in startDate.time..endDate.time step 86400000) {
            data.add(DateEntry(sdf.format(i), "00:00", "00:00"))
        }

        dateEntryAdapter.submitList(data)
        dateEntryAdapter.notifyDataSetChanged()
    }
}
