package com.example.learnenglish.ui.practice

import android.content.Intent
import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_practice_topic.*
import kotlinx.android.synthetic.main.include_toolbar.*

class PracticeTopicActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_topic)

        setTitleActionBar(toolbar, "Ôn luyện và đánh giá")

        cvtoeic.setOnClickListener {
            startActivity(Intent(this, ToeicTopicActivity::class.java))
        }

        cvIelts.setOnClickListener {

        }
    }
}