package com.example.otopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        ref = FirebaseDatabase.getInstance().getReference("artikel")

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent(this, show::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val judul = etJudul.text.toString()
        val detail = etDetail.text.toString()

        val idArtikel = ref.push().key.toString()
        val artikel = Artikel(idArtikel,judul,detail)

        ref.child(idArtikel).setValue(artikel).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}