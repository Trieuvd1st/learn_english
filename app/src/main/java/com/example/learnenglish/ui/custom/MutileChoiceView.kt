package com.example.learnenglish.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.layout_mutile_choice_view.view.*

class MutileChoiceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    var myChoice: Int = 0
    var have4Choice = true

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mutile_choice_view, this, true)
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.MutileChoiceView, 0, 0)
        have4Choice = a.getBoolean(R.styleable.MutileChoiceView_haveFourChoice, true)

        btnDA1.setOnClickListener {
            refreshView()
            changeViewAnswer(btnDA1, true)
            myChoice = 1
        }

        btnDA2.setOnClickListener {
            refreshView()
            changeViewAnswer(btnDA2, true)
            myChoice = 2
        }

        btnDA3.setOnClickListener {
            refreshView()
            changeViewAnswer(btnDA3, true)
            myChoice = 3
        }

        btnDA4.setOnClickListener {
            refreshView()
            changeViewAnswer(btnDA4, true)
            myChoice = 4
        }

        if (have4Choice) {
            btnDA4.visibility = View.VISIBLE
        } else {
            btnDA4.visibility = View.GONE
        }
    }

    private fun refreshView() {
        changeViewAnswer(btnDA1, false)
        changeViewAnswer(btnDA2, false)
        changeViewAnswer(btnDA3, false)
        changeViewAnswer(btnDA4, false)
    }

    private fun changeViewAnswer(textView: TextView, isSelected: Boolean) {
        when (isSelected) {
            true -> {
                textView.setBackgroundResource(R.drawable.bg_orange_corner_hard)
                textView.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked_true, 0, 0, 0)
            }

            false -> {
                textView.setBackgroundResource(R.drawable.bg_gray_corner_hard)
                textView.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked_false, 0, 0, 0)
            }
        }
    }

    fun setChoice1(value: String) {
        btnDA1.text = value
    }

    fun setChoice2(value: String) {
        btnDA2.text = value
    }

    fun setChoice3(value: String) {
        btnDA3.text = value
    }

    fun setChoice4(value: String) {
        btnDA4.text = value
    }
}