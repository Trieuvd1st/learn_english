package com.example.learnenglish.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.learnenglish.R
import com.example.learnenglish.fragment.SoundToTextFragment

class CommTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comm_test)

        showSoundToText()
    }

    private fun showSoundToText() {
        supportFragmentManager.beginTransaction().replace(R.id.frameTest, SoundToTextFragment()).commit()
    }

    companion object {
        fun startNewActivity(context: Context) {
            context.startActivity(Intent(context, CommTestActivity::class.java).apply {

            })
        }
    }
}