package com.example.learnenglish.ui.vocabulary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyItemDatabase
import kotlinx.android.synthetic.main.activity_flash_card.*

class FlashCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)

        val vocabularyItemDatabase = VocabularyItemDatabase(this)
        val listVocabularyItem = vocabularyItemDatabase.getListVocabularyItem(2)

        val flashCardPagerAdapter = FlashCardPagerAdapter(supportFragmentManager, listVocabularyItem)
        vpFlashCard.adapter = flashCardPagerAdapter
    }

    companion object {
        fun startNewActivity(context: Context) {
            context.startActivity(Intent(context, FlashCardActivity::class.java).apply {

            })
        }
    }
}