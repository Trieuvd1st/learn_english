package com.example.learnenglish.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.learnenglish.R
import com.example.learnenglish.utils.DisplayUtils
import kotlinx.android.synthetic.main.minus_point_dialog.*

class MinusPointDialog(
    context: Context,
    val pointRequired: Int,
    val myPoint: Int,
    val listener: MinusDialogListener?
) : Dialog(context) {

    interface MinusDialogListener {

        fun onOk()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.minus_point_dialog)

        tvAlertPointRequired.text = DisplayUtils.fromHtml(
            String.format(
                context.getString(R.string.alert_point_required),
                pointRequired
            )
        )
        tvMsgMyPoint.text = DisplayUtils.fromHtml(
            String.format(
                context.getString(R.string.message_my_point_have),
                myPoint
            )
        )

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnOk.setOnClickListener {
            listener?.onOk()
            dismiss()
        }
    }
}