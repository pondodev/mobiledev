package com.example.imageinfoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ImageInfo
import android.content.Intent
import android.text.TextUtils.split
import android.view.View
import kotlinx.android.synthetic.main.activity_view_image_info.*

class ViewImageInfo : AppCompatActivity() {

    var index = 0
    var doggos = ArrayList<ImageInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image_info)

        index = intent.getIntExtra("index", 0)
        doggos = intent.getParcelableArrayListExtra("doggos")

        // set state of everything on the page
        txtName.setText(doggos[index].name)
        txtLocation.setText(doggos[index].location)
        txtKeywords.setText(doggos[index].keywords)
        txtEmail.setText(doggos[index].email)
        chkbxShare.isChecked = doggos[index].share
        ratingBar.rating = doggos[index].rating
    }

    fun save(view: View) {
        doggos[index].name = txtName.text.toString()
        doggos[index].location = txtLocation.text.toString()
        doggos[index].keywords = txtKeywords.text.toString()
        doggos[index].email = txtEmail.text.toString()
        doggos[index].share = chkbxShare.isChecked
        doggos[index].rating = ratingBar.rating
        doggos[index].date = dateImageObtained.dayOfMonth.toString()
        doggos[index].date += "/" + (dateImageObtained.month + 1).toString()
        doggos[index].date += "/" + dateImageObtained.year.toString()

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }
}
