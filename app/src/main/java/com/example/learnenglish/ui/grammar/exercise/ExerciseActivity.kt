package com.example.learnenglish.ui.grammar.exercise

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.learnenglish.R
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.model.Exercise
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ExerciseActivity: BaseActivity() {

    private lateinit var grammarDatabase: GrammarDatabase

    private var listExercise: ArrayList<Exercise>? = null
    private  var currentpositon: Int = 0
    private var ansSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val idSelected = intent.getIntExtra("GRAMMAR_EXERCISE_ID", 0)

        grammarDatabase = GrammarDatabase(this)
        listExercise = grammarDatabase.getListGrammarExercise()

        currentpositon = idSelected - 1
        showQuestion()

        onClick()
    }

    private fun onClick() {
        btnDA1.setOnClickListener {
            ansSelected = 1
            changeViewAnswer(btnDA1, true)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, false)
        }

        btnDA2.setOnClickListener {
            ansSelected = 2
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, true)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, false)
        }

        btnDA3.setOnClickListener {
            ansSelected = 3
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, true)
            changeViewAnswer(btnDA4, false)
        }

        btnDA4.setOnClickListener {
            ansSelected = 4
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, true)
        }

        btnCorrectAns.setOnClickListener {
            if (ansSelected == listExercise?.get(currentpositon)?.correctAns) {
                convertChoiceNumberToView(ansSelected).setBackgroundResource(R.drawable.bg_blue_corner_hard)
            } else {
                convertChoiceNumberToView(listExercise?.get(currentpositon)?.correctAns!!).setBackgroundResource(R.drawable.bg_red_corner_hard)
                convertChoiceNumberToView(listExercise?.get(currentpositon)?.correctAns!!).setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }

        btnPrevious.setOnClickListener {
            if (currentpositon > 0) {
                currentpositon -= 1
                showQuestion()
            }
        }

        btnNext.setOnClickListener {
            if (currentpositon < listExercise?.size!!-1) {
                currentpositon += 1
                showQuestion()
            }
        }
    }

    private fun showQuestion() {
        listExercise?.let {
            setTitleActionBar(toolbar, "Bài tập - ${currentpositon+1}/${listExercise?.size}")
            tvQuestion.text = it[currentpositon].question
            btnDA1.text = it[currentpositon].ansA
            btnDA2.text = it[currentpositon].ansB
            btnDA3.text = it[currentpositon].ansC
            btnDA4.text = it[currentpositon].ansD
            formatViewChoice(btnDA1)
            formatViewChoice(btnDA2)
            formatViewChoice(btnDA3)
            formatViewChoice(btnDA4)
        }
    }

    private fun changeViewAnswer(textView: TextView, isSelected: Boolean) {
        when (isSelected) {
            true -> {
                textView.setBackgroundResource(R.drawable.bg_orange_corner_hard)
                textView.setTextColor(ContextCompat.getColor(this, R.color.white))
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked_true, 0, 0, 0)
            }

            false -> {
                textView.setBackgroundResource(R.drawable.bg_gray_corner_hard)
                textView.setTextColor(ContextCompat.getColor(this, R.color.black))
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked_false, 0, 0, 0)
            }
        }
    }

    private fun formatViewChoice(textView: TextView) {
        textView.setBackgroundResource(R.drawable.bg_gray_corner_hard)
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked_false, 0, 0, 0)
    }

    private fun convertChoiceNumberToView(choiceNumber: Int): TextView {
        return when(choiceNumber) {
            1 -> btnDA1
            2 -> btnDA2
            3 -> btnDA3
            else -> btnDA4
        }
    }
}