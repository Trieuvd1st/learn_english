package com.example.learnenglish.ui.vocabulary.topic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.vocabulary.search.VocaSearchActivity
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
        supportFragmentManager.findFragmentById(R.id.frameContent) ?: VocabularyFragment()
            .apply {

        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.voca_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                startActivity(Intent(this, VocaSearchActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}