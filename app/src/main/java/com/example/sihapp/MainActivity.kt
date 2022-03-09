package com.example.sihapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { scanQrCode.launch(null) }


    }

    fun handleResult(result: QRResult) {
        try {


        var code = result.toString().subSequence(33,37)
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show()
        if (code.length == 4){
            startActivity(Intent(this,MonumentMainScreen::class.java).putExtra("ID",code))
        }
        else{
            Toast.makeText(this,"this is not a correct QR", Toast.LENGTH_SHORT).show()
        }
        }
        catch (e:Exception){
            Toast.makeText(this,"this is not a correct QR", Toast.LENGTH_SHORT).show()
        }

    }

}