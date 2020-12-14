package com.example.learnenglish.ui.vocabulary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyItemDatabase
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_flash_card.*
import kotlinx.android.synthetic.main.include_toolbar.*

class FlashCardActivity : BaseActivity() {

    private var listVocabularyItem = mutableListOf<VocabularyItem>()
    private var totalItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_card)

        setTitleActionBar(toolbar, "Flash card")

        val topicId = intent.getIntExtra("VOCA_ITEM_FLASH_CARD", 0)
        val vocabularyItemDatabase = VocabularyItemDatabase(this)
        listVocabularyItem = vocabularyItemDatabase.getListVocabularyItem(topicId)
        totalItem = listVocabularyItem.size

        val listVocabularyItem = vocabularyItemDatabase.getListVocabularyItem(topicId)

        val flashCardPagerAdapter = FlashCardPagerAdapter(supportFragmentManager, listVocabularyItem)
        vpFlashCard.adapter = flashCardPagerAdapter

        tvCount.text = "1/${listVocabularyItem.size}"
        vpFlashCard.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                tvCount.text = "${position+1}/${listVocabularyItem.size}"
            }
            override fun onPageScrollStateChanged(state: Int) { }

        })
    }

    companion object {
        fun startNewActivity(context: Context) {
            context.startActivity(Intent(context, FlashCardActivity::class.java).apply {

            })
        }
    }
}