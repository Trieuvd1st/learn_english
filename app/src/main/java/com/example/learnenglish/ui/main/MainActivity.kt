package com.example.learnenglish.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.ui.authentication.profile.ProfileActivity
import com.example.learnenglish.ui.authentication.signin.SignInActivity
import com.example.learnenglish.ui.communication.CommunicationTopicActivity
import com.example.learnenglish.ui.grammar.GrammarTopicActivity
import com.example.learnenglish.ui.practice.PracticeTopicActivity
import com.example.learnenglish.ui.translate.TranslateActivity
import com.example.learnenglish.ui.vocabulary.topic.VocabularyActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var vocabularyDatabase: VocabularyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.let {
            tvTitleToolbar.text = title
            it.title = ""
        }

        vocabularyDatabase = VocabularyDatabase(this)
        val database: File = getDatabasePath(VocabularyDatabase.DBNAME)
        if (!database.exists()) {
            vocabularyDatabase.readableDatabase
            if (vocabularyDatabase.copyDatabase(this)) {
                //Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show()
            }
        }

        cvVoca.setOnClickListener(this)
        cvGrammar.setOnClickListener(this)
        cvCommunication.setOnClickListener(this)
        cvTranslate.setOnClickListener(this)
        cvPractice.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            cvVoca.id -> {
                startActivity(Intent(this, VocabularyActivity::class.java))
            }

            cvGrammar.id -> {
                startActivity(Intent(this, GrammarTopicActivity::class.java))
            }

            cvCommunication.id -> {
                startActivity(Intent(this, CommunicationTopicActivity::class.java))
            }

            cvTranslate.id -> {
                startActivity(Intent(this, TranslateActivity::class.java))
            }

            cvPractice.id -> {
                startActivity(Intent(this, PracticeTopicActivity::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_login -> {
                if (Firebase.auth.currentUser?.isAnonymous == null) {
                    startActivity(Intent(this, SignInActivity::class.java))
                } else {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}