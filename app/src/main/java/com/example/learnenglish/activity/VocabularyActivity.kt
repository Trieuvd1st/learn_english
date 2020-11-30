package com.example.learnenglish.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.extension.intentToFragmentArguments
import com.example.learnenglish.extension.replaceFragmentInActivity
import com.example.learnenglish.fragment.VocabularyFragment

class VocabularyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        replaceFragmentInActivity(findOrCreateViewFragment(), R.id.frameContent)
    }

    private fun findOrCreateViewFragment() =
            supportFragmentManager.findFragmentById(R.id.frameContent) ?: VocabularyFragment().apply {
            }
}