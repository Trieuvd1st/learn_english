package com.example.learnenglish.ui.translate

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.activity_translate.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.IOException

class TranslateActivity : BaseActivity() {

    private lateinit var enToViTranslator: Translator
    private lateinit var viToEnTranslator: Translator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        setTitleActionBar(toolbar, "Dịch Văn bản")

        downloadTranslate()

        btnEnToVi.setOnClickListener {
            enToViTranslator.translate(edtInputText.text.toString())
                .addOnSuccessListener { translatedText ->
                    Log.d("TRANSLATE_TEXT", translatedText)
                    tvTextTranslated.text = translatedText
                }
                .addOnFailureListener { exception ->
                    Log.d("TRANSLATE_TEXT", "on error")
                    // Error.
                    // ...
                }
        }

        btnViToEn.setOnClickListener {
            viToEnTranslator.translate(edtInputText.text.toString())
                .addOnSuccessListener { translatedText ->
                    Log.d("TRANSLATE_TEXT", translatedText)
                    tvTextTranslated.text = translatedText
                }
                .addOnFailureListener { exception ->
                    Log.d("TRANSLATE_TEXT", "on error")
                    // Error.
                    // ...
                }
        }
    }

    private fun downloadTranslate() {
        val options1 = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.VIETNAMESE)
            .build()

        val options2 = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.VIETNAMESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        enToViTranslator = Translation.getClient(options1)
        viToEnTranslator = Translation.getClient(options2)

        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        enToViTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {  }
            .addOnFailureListener { exception -> }

        viToEnTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {  }
            .addOnFailureListener { exception -> }
    }
}