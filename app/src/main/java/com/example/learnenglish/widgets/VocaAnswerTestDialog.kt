package com.example.learnenglish.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.dialog_voca_answer_test.*

class VocaAnswerTestDialog(context: Context, var isCorrect: Boolean, var answer: String) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_voca_answer_test)

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                    (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
                    WindowManager.LayoutParams.WRAP_CONTENT
            )
        }

        when (isCorrect) {
            true -> {
                llCorrect.isVisible = true
                llIncorrect.isVisible = false
            }
            false -> {
                llCorrect.isVisible = false
                llIncorrect.isVisible = true
            }
        }
    }
}