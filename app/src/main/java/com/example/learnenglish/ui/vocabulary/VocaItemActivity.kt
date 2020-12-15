package com.example.learnenglish.ui.vocabulary

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.database.VocabularyItemDatabase
import com.example.learnenglish.model.Vocabulary
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.vocabulary.vocatest.VocaTestActivity
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

        vocabularyItemDatabase = VocabularyItemDatabase(this)
        vocabularyDatabase = VocabularyDatabase(this)
        //Toast.makeText(this, vocabulary.getIdVocabulary()+" "+level, Toast.LENGTH_SHORT).show();
        vocabulary?.let {
            itemArrayList = vocabularyItemDatabase!!.getListVocabularyItem(it.idVocabulary)
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