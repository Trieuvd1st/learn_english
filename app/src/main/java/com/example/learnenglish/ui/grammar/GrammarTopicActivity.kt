package com.example.learnenglish.ui.grammar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.model.Grammar
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.grammar.exercise.ExerciseTopicGrammarActivity
import kotlinx.android.synthetic.main.activity_grammar_topic.*
import kotlinx.android.synthetic.main.include_toolbar.*

class GrammarTopicActivity : BaseActivity() {

    private lateinit var grammarDatabase: GrammarDatabase
    private var listGrammar: ArrayList<Grammar>? = null
    private lateinit var adapterGrammar: GrammarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_topic)

        setTitleActionBar(toolbar, " Ngữ pháp")

        grammarDatabase = GrammarDatabase(this)
        listGrammar = grammarDatabase.getListGrammar()

        adapterGrammar = GrammarAdapter(listGrammar!!)

        rvGrammarTopic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterGrammar
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.grammar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exercise -> {
                startActivity(Intent(this, ExerciseTopicGrammarActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
