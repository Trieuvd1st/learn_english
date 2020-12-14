package com.example.learnenglish.ui.communication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_topic_communication.*
import kotlinx.android.synthetic.main.include_toolbar.*

class CommunicationTopicActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_communication)

        setTitleActionBar(toolbar, "Tiếng anh giao tiếp")

        llGreetingTopic.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 1, "Chào hỏi")
        }

        llNumber.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 2, "Con số")
        }

        llTime.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 3, "Thời gian")
        }

        llTravel.setOnClickListener {

        }

        llFriend.setOnClickListener {

        }

        llEntertaiment.setOnClickListener {

        }
    }
}