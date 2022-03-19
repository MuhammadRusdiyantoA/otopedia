package com.example.otopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class show : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Artikel>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        supportActionBar?.hide()

        ref = FirebaseDatabase.getInstance().getReference("artikel")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        val keyword = intent.getStringExtra("keyword")
        val etSearch = findViewById<EditText>(R.id.kwSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearchKeyword)

        etSearch.setText(keyword)

        btnSearch.setOnClickListener {
            Toast.makeText(this, "Search function coming soon...", Toast.LENGTH_SHORT).show()
        }

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val artikel = h.getValue(Artikel::class.java)
                        list.add(artikel!!)
                    }
                    val adapter = Adapter(this@show,R.layout.users,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}