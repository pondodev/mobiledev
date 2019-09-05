package com.example.imageinfoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ImageInfo
import android.content.Intent
import android.provider.ContactsContract
import android.view.View

class ViewImageInfo : AppCompatActivity() {

    var index = 0
    var doggos = ArrayList<ImageInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image_info)

        index = intent.getIntExtra("index", 0)
        doggos = intent.getParcelableArrayListExtra("doggos")
    }

    fun save(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("doggos", doggos)
        }

        startActivity(intent)
    }
}
