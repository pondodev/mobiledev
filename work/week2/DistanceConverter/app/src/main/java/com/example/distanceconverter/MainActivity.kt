package com.example.distanceconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConvert.setOnClickListener {
            convertUnits()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("output", lblConvertDisplay.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lblConvertDisplay.text = savedInstanceState.getString("output")
    }

    // called when we convert the given units
    private fun convertUnits() {
        // store each of our values
        val miles = txtMiles.text.toString()
        val feet = txtFeet.text.toString()
        val inches = txtInches.text.toString()

        // check if the user even entered a value
        if (miles == "" && feet == "" && inches == "") {
            lblConvertDisplay.text = getString(R.string.novalue_display)
            return
        }

        // awful check to see we don't have more than one value entered at a time
        if ((miles != "" && feet != "") ||
            (miles != "" && inches != "") ||
            (feet != "" && inches != "")) {
            lblConvertDisplay.text = getString(R.string.multivalue_display)
            return
        }

        // from here we are now safe to assume that there is only one value entered and we can start converting
        var res = 0.0

        when {
            miles.isNotEmpty() -> res = milesToCentimetres(miles.toDouble())
            feet.isNotEmpty() -> res = feetToCentimetres(feet.toDouble())
            inches.isNotEmpty() -> res = inchesToCentimetres(inches.toDouble())
        }

        // check if we need to now convert to metres
        if (chkbxToggleUnits.isChecked) {
            res = centimetresToMetres(res)
            lblConvertDisplay.text = String.format(getString(R.string.metres_display), res)
        } else {
            lblConvertDisplay.text = String.format(getString(R.string.centimetres_display), res)
        }
    }

    // convert miles to centimetres
    private fun milesToCentimetres(num: Double): Double {
        return num * 160934.4
    }

    // convert feet to centimetres
    private fun feetToCentimetres(num: Double): Double {
        return num * 30.48
    }

    // convert inches to centimetres
    private fun inchesToCentimetres(num: Double): Double {
        return num * 2.54
    }

    // convert centimetres to metres
    private fun centimetresToMetres(num: Double): Double {
        return num / 100
    }
}
