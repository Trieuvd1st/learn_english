package com.example.learnenglish.ui.communication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.model.Communication
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
            CommunicationActivity.startNewActivity(this, 4, "Du lịch")
        }

        llFriend.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 5, "Kết bạn")
        }

        llEntertaiment.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 6, "Giải trí")
        }

        llEating.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 7, "Giải trí")
        }

        llShopping.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 8, "Mua sắm")
        }

        llWeather.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 9, "Thời tiết")
        }

        llJob.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 10, "Việc làm")
        }

        llHeath.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 11, "Sức khỏe")
        }

        llComm.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 12, "Giao tiếp")
        }

        llLocation.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 13, "Địa điểm")
        }

        llHouse.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 14, "Nhà ở")
        }

        llAlways.setOnClickListener {
            CommunicationActivity.startNewActivity(this, 15, "Thường dùng")
        }
    }
}