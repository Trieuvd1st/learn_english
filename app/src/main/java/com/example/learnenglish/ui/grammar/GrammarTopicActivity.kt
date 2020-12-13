package com.example.learnenglish.ui.grammar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.model.Grammar
import kotlinx.android.synthetic.main.activity_grammar_topic.*

class GrammarTopicActivity : AppCompatActivity() {

    private lateinit var grammarDatabase: GrammarDatabase
    private var listGrammar: ArrayList<Grammar>? = null
    private lateinit var adapterGrammar: GrammarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_topic)

        grammarDatabase = GrammarDatabase(this)
        listGrammar = grammarDatabase.getListGrammar()

        adapterGrammar = GrammarAdapter(listGrammar!!)

        rvGrammarTopic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterGrammar
        }
    }
}
