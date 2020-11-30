package com.example.learnenglish.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.activity_topic_communication.*

class CommunicationTopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_communication)

        supportActionBar?.title = "Tiếng anh giao tiếp"

        llTopic.setOnClickListener {
            startActivity(Intent(this, CommunicationActivity::class.java))
        }
    }
}