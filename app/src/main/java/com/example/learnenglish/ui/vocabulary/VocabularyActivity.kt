package com.example.learnenglish.ui.vocabulary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.ui.communication.test.VocabularyFragment
import com.example.learnenglish.utils.extension.replaceFragmentInActivity

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