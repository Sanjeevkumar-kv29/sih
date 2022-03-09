package com.example.sihapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_done_screen.*

class DoneScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done_screen)
        val id = intent.getStringExtra("number")

        donemsg.text = "Done "+id+" number if entries are allowed"

    }
}