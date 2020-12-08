package com.example.learnenglish.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.learnenglish.R
import com.example.learnenglish.model.Communication
import kotlinx.android.synthetic.main.dialog_voice.*
import kotlinx.android.synthetic.main.item_communication.ivSpeaker
import java.util.*

class VoiceDialog(context: Context, val comm: Communication): Dialog(context) {

    private lateinit var speechRecognizer: SpeechRecognizer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_voice)

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }

        tvTitle.text = comm.enSentence
        ivSpeaker.setOnClickListener {
            val resourceFromName = context.resources.getIdentifier(
                comm.nameSound,
                "raw",
                context.packageName
            )
            val mediaPlayer = MediaPlayer.create(context, resourceFromName)
            mediaPlayer.start()
        }

        btnClose.setOnClickListener {
            dismiss()
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {
                tvResult.visibility = View.VISIBLE
                tvResult.text = "Listening..."
            }

            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {}
            override fun onError(i: Int) {}
            override fun onResults(bundle: Bundle) {
                ivVoice.setImageResource(R.drawable.ic_mic_no_active)
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                tvResult.visibility = View.VISIBLE
                Log.d("DETECT_VOICE_TO_TEXT", "${data!![0]}, ${comm.enSentence.substring(0, comm.enSentence.length - 1)}")
                if (data[0].equals(comm.enSentence.substring(0, comm.enSentence.length - 1), ignoreCase = true)) {
                    tvResult.text = "Chính xác"
                    tvResult.setTextColor(ContextCompat.getColor(context, R.color.blue_dark))
                } else {
                    tvResult.text = "Chưa đúng. Mời thử lại"
                    tvResult.setTextColor(ContextCompat.getColor(context, R.color.red_bright))
                }
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
        })

        ivVoice.setOnTouchListener(OnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                speechRecognizer.stopListening()
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                ivVoice.setImageResource(R.drawable.ic_mic_active)
                speechRecognizer.startListening(speechRecognizerIntent)
            }
            false
        })
    }

    override fun onStop() {
        super.onStop()
        speechRecognizer.destroy()
    }
}