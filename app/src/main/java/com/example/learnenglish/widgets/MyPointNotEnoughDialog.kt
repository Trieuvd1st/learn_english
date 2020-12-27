package com.example.learnenglish.widgets

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.learnenglish.R
import kotlinx.android.synthetic.main.minus_point_dialog.*

class MyPointNotEnoughDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_my_point_not_enough)

        btnOk.setOnClickListener {
            dismiss()
        }
    }
}