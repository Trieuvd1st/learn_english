package com.example.learnenglish.ui.communication.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.model.Communication
import kotlinx.android.synthetic.main.dialog_voice.*
import kotlinx.android.synthetic.main.fragment_en_sen_to_mic.*
import java.util.*

class EnSenToMicFragment : Fragment() {

    private lateinit var viewModelCommTest: CommTestViewModel
    private lateinit var speechRecognizer: SpeechRecognizer
    private var currentComm = Communication()
    private var result = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_en_sen_to_mic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()

        setMicOn()
    }

    private fun initViewModel() {
        viewModelCommTest = ViewModelProviders.of(activity!!).get(CommTestViewModel::class.java).apply {
            commAnswerData.observe(this@EnSenToMicFragment, Observer {
                tvQuestion.text = it.enSentence
                currentComm = it
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setMicOn() {
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {
                /*tvResult.visibility = View.VISIBLE
                tvResult.text = "Listening..."*/
            }

            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {}
            override fun onError(i: Int) {}
            override fun onResults(bundle: Bundle) {
                ivVoice.setImageResource(R.drawable.ic_mic_no_active)
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                tvResult.visibility = View.VISIBLE
                Log.d("DETECT_VOICE_TO_TEXT", "${data!![0]}, ${currentComm.enSentence.substring(0, currentComm.enSentence.length - 1)}")
                result = data[0]
                viewModelCommTest.setChoicePicked(result)
            }

            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
        })

        ivMic.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                speechRecognizer.stopListening()
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                ivMic.setImageResource(R.drawable.ic_mic_active)
                speechRecognizer.startListening(speechRecognizerIntent)
            }
            false
        }
    }
}