package com.example.learnenglish.ui.practice

import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_toeic_result.*

class ToeicResultActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toeic_result)

        val score = intent.getIntExtra("TOEIC_SCORE", -1)
        tvScore.text = "my score is $score"
    }
}