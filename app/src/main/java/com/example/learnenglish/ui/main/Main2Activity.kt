package com.example.learnenglish.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.ui.communication.CommunicationTopicActivity
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        llVocabulary.setOnClickListener(this)
        llGrammar.setOnClickListener(this)
        llCommunication.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            llVocabulary.id -> { startActivity(Intent(this, MainActivity::class.java))}

            llGrammar.id -> { startActivity(Intent(this, CommunicationTopicActivity::class.java)) }

            llCommunication.id -> { startActivity(Intent(this, CommunicationTopicActivity::class.java)) }
        }
    }
}