package com.example.learnenglish.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.item_communication.*

class CommunicationActivity : AppCompatActivity() {

    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        tvHello.setOnClickListener {
            isOpen = !isOpen
            if (isOpen) {
                clEn.visibility = View.VISIBLE
            } else {
                clEn.visibility = View.GONE
            }
        }
        ivSpeaker.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.good_evening_sir)
            mediaPlayer.start()
        }
    }
}