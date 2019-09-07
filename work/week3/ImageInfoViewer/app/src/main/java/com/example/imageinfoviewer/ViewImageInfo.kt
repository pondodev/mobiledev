package com.example.imageinfoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ImageInfo
import android.content.Intent
import android.view.View
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validUrl
import kotlinx.android.synthetic.main.activity_view_image_info.*
import org.jetbrains.anko.longToast

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
        // do some error checking first
        var errors = false
        if (txtName.text.toString() == "") {
            errors = true
            longToast("the name field cannot be blank")
        }

        if (txtLocation.text.toString() == "" || !txtLocation.validUrl()) {
            errors = true
            longToast("you must enter a valid URL")
        }

        if (txtEmail.text.toString() == "" || !txtEmail.validEmail()) {
            errors = true
            longToast("you must enter a valid email")
        }

        // we don't want to continue any further if we have any errors present
        if (errors)
            return

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
