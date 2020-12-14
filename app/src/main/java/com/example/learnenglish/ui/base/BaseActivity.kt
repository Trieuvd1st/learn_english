package com.example.learnenglish.ui.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.include_toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    fun setTitleActionBar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.let {
            tvTitleToolbar.text = title
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}