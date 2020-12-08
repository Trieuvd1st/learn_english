package com.example.learnenglish.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.activity_topic_communication.*

class CommunicationTopicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_communication)

        supportActionBar?.title = "Tiếng anh giao tiếp"

        llGreetingTopic.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 1)
        }

        llNumber.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 2)
        }

        llTime.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 3)
        }
    }
}