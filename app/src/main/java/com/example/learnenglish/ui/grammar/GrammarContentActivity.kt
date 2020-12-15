package com.example.learnenglish.ui.grammar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.model.Grammar
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.utils.DisplayUtils
import kotlinx.android.synthetic.main.activity_grammar_content.*
import kotlinx.android.synthetic.main.include_toolbar.*

class GrammarContentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_content)

        val grammar: Grammar = intent.getSerializableExtra(GRAMMAR_EXTRA) as Grammar
        setTitleActionBar(toolbar, grammar.topic)

        tvContent.text = DisplayUtils.fromHtml(grammar.detailContent)
        Log.d("GRAMMAR_CONTENT", "${grammar.detailContent}")
    }

    companion object {
        fun startNewActivity(context: Context, grammar: Grammar) {
            context.startActivity(Intent(context, GrammarContentActivity::class.java).apply {
                putExtra(GRAMMAR_EXTRA, grammar)
            })
        }

        private const val GRAMMAR_EXTRA = "com.example.learnenglish.ui.grammar.GRAMMAR_EXTRA"
    }
}