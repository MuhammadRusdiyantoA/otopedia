package com.example.otopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.hide()

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val linkCreate = findViewById<TextView>(R.id.linkCreate)

        btnSearch.setOnClickListener {
            if (etSearch.text.isEmpty()) {
                etSearch.error = "Please enter something..."
                etSearch.requestFocus()
            }
            else {
                val intent = Intent(this, show::class.java)
                intent.putExtra("keyword", etSearch.text.toString().trim())
                startActivity(intent)
            }
        }

        linkCreate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}