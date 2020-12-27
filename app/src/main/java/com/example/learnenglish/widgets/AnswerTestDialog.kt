package com.example.learnenglish.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.view.isVisible
import com.example.learnenglish.R
import com.example.learnenglish.database.UserManager
import kotlinx.android.synthetic.main.dialog_answer_test.*

class AnswerTestDialog(context: Context, var isCorrect: Boolean, var answer: String, val listener: VoCaAnswerTestDialogListener) : Dialog(context) {

    interface VoCaAnswerTestDialogListener {
        fun onBtnNext()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_answer_test)

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                    (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
                    WindowManager.LayoutParams.WRAP_CONTENT
            )
        }

        btnNext.setOnClickListener {
            listener.onBtnNext()
            dismiss()
        }

        when (isCorrect) {
            true -> {
                UserManager.setMyPoint(context, UserManager.getMyPoint(context) + 1)
                llCorrect.isVisible = true
                llIncorrect.isVisible = false
            }
            false -> {
                llCorrect.isVisible = false
                llIncorrect.isVisible = true
            }
        }

        tvResult.text = "Đáp án : $answer"
    }
}