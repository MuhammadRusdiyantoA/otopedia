package com.example.otopedia

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Artikel> )
    : ArrayAdapter<Artikel>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val container = view.findViewById<LinearLayout>(R.id.container)

        val textJudul = view.findViewById<TextView>(R.id.textJudul)
        val textDetail = view.findViewById<TextView>(R.id. textDetail)

        val textUpdate = view.findViewById<TextView>(R.id.TextUpdate)
        val textDelete = view.findViewById<TextView>(R.id.TextDelete)

        val artikel = list[position]

        textJudul.text = artikel.judul
        textDetail.text = artikel.detail

        if (artikel.judul.length > 50) {
            val shortJudul = "${artikel.judul.subSequence(0, 50)}..."
            textJudul.text = shortJudul
        }

        if (artikel.detail.length > 200) {
            val shortDetail = "${artikel.detail.subSequence(0, 200)}..."
            textDetail.text = shortDetail
        }

        container.setOnClickListener {
            ReadArticle(artikel)
        }

        textUpdate.setOnClickListener {
            showUpdateDialog(artikel)
        }
        textDelete.setOnClickListener {
            Deleteinfo(artikel)
        }

        return view
    }

    private fun ReadArticle(artikel: Artikel) {
        val intent = Intent(context, read::class.java)
        intent.putExtra("judul", artikel.judul)
        intent.putExtra("detail", artikel.detail)
        context.startActivity(intent)
    }

    private fun Deleteinfo(artikel: Artikel) {
        val progressDialog = ProgressDialog(context, R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()

        val mydatabase = FirebaseDatabase.getInstance().getReference("artikel")
        mydatabase.child(artikel.id).removeValue()
        progressDialog.cancel()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()

//        val intent = Intent(context, show::class.java)
//        context.startActivity(intent)
    }

    private fun showUpdateDialog(artikel: Artikel) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textJudul = view.findViewById<EditText>(R.id.etJudul)
        val textDetail = view.findViewById<EditText>(R.id.etDetail)

        textJudul.setText(artikel.judul)
        textDetail.setText(artikel.detail)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("artikel")

            val judul = textJudul.text.toString().trim()

            val detail = textDetail.text.toString().trim()

            if (judul.isEmpty()){
                textJudul.error = "please enter name"
                textJudul.requestFocus()
                return@setPositiveButton
            }

            if (detail.isEmpty()){
                textDetail.error = "please enter status"
                textDetail.requestFocus()
                return@setPositiveButton
            }

            val artikel = Artikel(artikel.id, judul, detail)

            dbUsers.child(artikel.id).setValue(artikel).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }
}