package com.example.learnenglish.ui.vocabulary.item

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.database.VocabularyItemDatabase
import com.example.learnenglish.model.Vocabulary
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.vocabulary.flashcard.FlashCardActivity
import com.example.learnenglish.ui.vocabulary.vocatest.VocaTestActivity
import com.example.learnenglish.utils.extension.isLogin
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_voca_item.*
import kotlinx.android.synthetic.main.include_toolbar.*

class VocaItemActivity : BaseActivity() {

    private lateinit var adapterVocaItem: VocaItemAdapter
    var vocabularyItemDatabase: VocabularyItemDatabase? = null
    var vocabularyDatabase: VocabularyDatabase? = null
    private var vocabulary: Vocabulary? = null
    private var itemArrayList = mutableListOf<VocabularyItem>()
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_item)

        getIncomingData()
        setTitleActionBar(toolbar, title)

        btnPratice.setOnClickListener {
            startActivity(Intent(this, VocaTestActivity::class.java).apply {
                putExtra("VOCA_ITEM_PRATICE", vocabulary?.idVocabulary)
            })
        }

        btnFlashCard.setOnClickListener {
            startActivity(Intent(this, FlashCardActivity::class.java).apply {
                putExtra("VOCA_ITEM_FLASH_CARD", vocabulary?.idVocabulary)
            })
        }

        tvNumberPoint.isVisible = !isLogin()
        ivAddPoint.isVisible = !isLogin()

        vocabularyItemDatabase = VocabularyItemDatabase(this)
        vocabularyDatabase = VocabularyDatabase(this)
        vocabulary?.let {
            itemArrayList = vocabularyItemDatabase!!.getListVocabularyItem(it.idVocabulary)
            tvNumberPoint.text = "+${itemArrayList.size}"
        }
        adapterVocaItem = VocaItemAdapter(itemArrayList)
        rvVocaItem.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterVocaItem
        }
    }

    private fun getIncomingData() {
        val intent: Intent = intent
        if (intent.hasExtra("position")) {
            vocabulary = intent.getSerializableExtra("position") as Vocabulary?
            title = intent.getStringExtra("vocabulary_title").toString()
        }
    }
}