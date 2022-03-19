package com.example.otopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class read : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        supportActionBar?.hide()

        val judul = intent.getStringExtra("judul")
        val detail = intent.getStringExtra("detail")

        val tvJudul = findViewById<TextView>(R.id.ReadJudul)
        val tvDetail = findViewById<TextView>(R.id.ReadDetail)

        tvJudul.text = judul
        tvDetail.text = detail
    }
}