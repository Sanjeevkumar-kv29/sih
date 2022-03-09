package com.example.sihapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_monument_main_screen.*
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MonumentMainScreen : AppCompatActivity() {
    val fsdb = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monument_main_screen)
        val id = intent.getStringExtra("ID")
        var monumentname = ""
        var price = ""

        val docRef = fsdb.collection("MONUMENTDETAILS").document(id.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    Log.d("email EXIST", "DocumentSnapshot data: ${document.data}")

                    monumentname = document.data!!.get("NAME").toString()
                    price = document.data!!.get("TICKETCOST").toString()

                    monname.text = monumentname
                    moncost.text = price

                } else {

                }
            }
            .addOnFailureListener { exception ->
                Log.d("ERROR OCCURRED","get failed with ", exception)
            }

        submit.setOnClickListener {

            val number = number.text.toString()
            FireStoreReg(number,monumentname, moncost.toString())
        }

    }

    fun FireStoreReg(numberofticket: String ,monumentname: String, customerid: String){

        val FSDB : FirebaseFirestore = FirebaseFirestore.getInstance()

        val datamap:MutableMap<String,Any> = HashMap<String,Any>()

        datamap.put("NUMBEROFTICKET",numberofticket)
        datamap.put("MONUMENT",monumentname)
        datamap.put("CUSTOMER",customerid)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        FSDB.collection("BOOKINGS").document(customerid+" "+currentDate).set(datamap)
            .addOnSuccessListener {
                Toast.makeText(this,"Done Entries Allow for "+numberofticket+" Peoples",Toast.LENGTH_LONG).show()

                startActivity(Intent(this,DoneScreen::class.java).putExtra("number",numberofticket))
            }
            .addOnFailureListener {
                Toast.makeText(this,"Connection ERROR",Toast.LENGTH_LONG).show()
            }


    }
}