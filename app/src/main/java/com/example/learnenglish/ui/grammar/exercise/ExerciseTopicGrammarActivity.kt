package com.example.learnenglish.ui.grammar.exercise

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.model.Exercise
import com.example.learnenglish.model.Grammar
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_exercise_topic_grammar.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ExerciseTopicGrammarActivity : BaseActivity() {

    private lateinit var grammarDatabase: GrammarDatabase
    private lateinit var adapterExerciseTopic: ExerciseTopicAdapter

    private var listExercise: ArrayList<Exercise>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_topic_grammar)

        setTitleActionBar(toolbar, "Bài tập")

        grammarDatabase = GrammarDatabase(this)
        listExercise = grammarDatabase.getListGrammarExercise()

        adapterExerciseTopic = ExerciseTopicAdapter(listExercise!!)
        rvExerciseTopic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterExerciseTopic
        }
    }
}