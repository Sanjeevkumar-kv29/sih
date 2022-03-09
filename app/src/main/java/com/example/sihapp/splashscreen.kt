package com.example.sihapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class splashscreen : AppCompatActivity() {

    var currentuser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val sharepref: SharedPreferences = getSharedPreferences("uidpass",0)
        currentuser = sharepref.getString("currentusermobile","").toString()
        var currentusercity = sharepref.getString("city","").toString()



        Handler().postDelayed(
            {
                if(currentuser!=null){
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                else {
                    startActivity(Intent(this, loginscreen::class.java))
                    overridePendingTransition(R.anim.leftout, R.anim.rightin)
                    finish()
                }

            },3000 )
    }
}