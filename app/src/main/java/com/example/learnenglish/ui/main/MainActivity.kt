package com.example.learnenglish.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.ui.communication.CommunicationTopicActivity
import com.example.learnenglish.ui.grammar.GrammarTopicActivity
import com.example.learnenglish.ui.practice.PracticeActivity
import com.example.learnenglish.ui.translate.TranslateActivity
import com.example.learnenglish.ui.vocabulary.VocabularyActivity
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var vocabularyDatabase: VocabularyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        vocabularyDatabase = VocabularyDatabase(this)
        val database: File = getDatabasePath(VocabularyDatabase.DBNAME)
        if (!database.exists()) {
            vocabularyDatabase.readableDatabase
            if (vocabularyDatabase.copyDatabase(this)) {
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show()
            }
        }

        llVocabulary.setOnClickListener(this)
        llGrammar.setOnClickListener(this)
        llCommunication.setOnClickListener(this)
        llTranslate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            llVocabulary.id -> {
                startActivity(Intent(this, VocabularyActivity::class.java))
            }

            llGrammar.id -> {
                startActivity(Intent(this, GrammarTopicActivity::class.java))
            }

            llCommunication.id -> {
                startActivity(Intent(this, CommunicationTopicActivity::class.java))
            }

            llTranslate.id -> {
                startActivity(Intent(this, PracticeActivity::class.java))
            }
        }
    }
}