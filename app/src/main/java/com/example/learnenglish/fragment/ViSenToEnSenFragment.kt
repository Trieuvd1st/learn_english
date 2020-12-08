package com.example.learnenglish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.activity.CommTestViewModel
import kotlinx.android.synthetic.main.fragment_en_sen_to_vi_sen.*

class ViSenToEnSenFragment : Fragment() {

    private lateinit var viewModelCommTest: CommTestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vi_sen_to_en_sen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onClick()

        initViewModel()
    }

    private fun onClick() {
        ivSpeaker.setOnClickListener {

        }

        btnDA1.setOnClickListener {
            viewModelCommTest.setChoicePicked(btnDA1.text.toString())
            changeViewAnswer(btnDA1, true)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, false)
        }

        btnDA2.setOnClickListener {
            viewModelCommTest.setChoicePicked(btnDA2.text.toString())
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, true)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, false)
        }

        btnDA3.setOnClickListener {
            viewModelCommTest.setChoicePicked(btnDA3.text.toString())
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, true)
            changeViewAnswer(btnDA4, false)
        }

        btnDA4.setOnClickListener {
            viewModelCommTest.setChoicePicked(btnDA4.text.toString())
            changeViewAnswer(btnDA1, false)
            changeViewAnswer(btnDA2, false)
            changeViewAnswer(btnDA3, false)
            changeViewAnswer(btnDA4, true)
        }
    }

    private fun initViewModel() {
        viewModelCommTest = ViewModelProviders.of(activity!!).get(CommTestViewModel::class.java).apply {
            commAnswerData.observe(this@ViSenToEnSenFragment, Observer {
                tvQuestion.text = it.viSentence
            })

            listChoiceData.observe(this@ViSenToEnSenFragment, Observer {
                btnDA1.text = it[0].enSentence
                btnDA2.text = it[1].enSentence
                btnDA3.text = it[2].enSentence
                btnDA4.text = it[3].enSentence
            })
        }
    }

    fun changeViewAnswer(textView: TextView, isSelected: Boolean) {
        when (isSelected) {
            true -> {
                textView.setBackgroundResource(R.drawable.bg_blue_corner_hard)
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
}