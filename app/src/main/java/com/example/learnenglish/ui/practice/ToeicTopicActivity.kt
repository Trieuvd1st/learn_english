package com.example.learnenglish.ui.practice

import android.content.Intent
import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_toeic_topic.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ToeicTopicActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toeic_topic)

        setTitleActionBar(toolbar, "Đề luyện tập")

        llDe1.setOnClickListener {
            startActivity(Intent(this, PracticeToeicActivity::class.java).apply {
                putExtra("EXTRA_TITLE_TOEIC_TOPIC", "Đề số 1")
            })
        }

        llDe2.setOnClickListener {  }

        llDe3.setOnClickListener {  }
    }
}