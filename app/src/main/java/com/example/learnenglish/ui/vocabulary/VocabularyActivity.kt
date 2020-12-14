package com.example.learnenglish.ui.vocabulary

import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.utils.extension.replaceFragmentInActivity
import kotlinx.android.synthetic.main.include_toolbar.*

class VocabularyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        setTitleActionBar(toolbar, "TỪ VỰNG")

        replaceFragmentInActivity(findOrCreateViewFragment(), R.id.frameContent)
    }

    private fun findOrCreateViewFragment() =
            supportFragmentManager.findFragmentById(R.id.frameContent) ?: VocabularyFragment().apply {
            }
}