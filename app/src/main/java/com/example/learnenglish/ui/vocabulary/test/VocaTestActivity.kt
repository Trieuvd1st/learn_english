package com.example.learnenglish.ui.vocabulary.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.VocabularyItemDatabase
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.model.WordChar
import kotlinx.android.synthetic.main.activity_voca_test.*

class VocaTestActivity : AppCompatActivity() {

    private lateinit var adapterVocaTest: VocaTestAdapter
    private var enResult: String = ""
    private var listVocabularyItem = mutableListOf<VocabularyItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_test)

        val vocabularyItemDatabase = VocabularyItemDatabase(this)
        listVocabularyItem = vocabularyItemDatabase.getListVocabularyItem(2)

        initAdapter()
        showTest()

        btnCheck.setOnClickListener {
            showTest()
        }
    }

    private fun initAdapter() {
        adapterVocaTest = VocaTestAdapter(mutableListOf())
        rvListChar.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 6)
            adapter = adapterVocaTest
        }

        adapterVocaTest.setListener(object : VocaTestAdapter.VocaTestAdapterListener {
            override fun onHeaderClick() {
                adapterVocaTest.showChar(tvEnResult.length() - 1)
                Log.d("TRIEUVD1", "last index: ${tvEnResult.length() - 1}")
                enResult = enResult.substring(0, enResult.length - 1)
                tvEnResult.text = enResult
                adapterVocaTest.isEnableBtnback = tvEnResult.text.isNotEmpty()
            }

            override fun onItemClick(wordChar: WordChar, pos: Int) {
                enResult += wordChar.text
                tvEnResult.text = enResult
                adapterVocaTest.hideChar(pos, tvEnResult.length() - 1)
                Log.d("TRIEUVD1", "last index new: ${tvEnResult.length() - 1}")
                adapterVocaTest.isEnableBtnback = tvEnResult.text.isNotEmpty()
            }
        })
    }

    private fun showTest() {
        enResult = ""
        tvEnResult.text = enResult
        if (listVocabularyItem.isNotEmpty()) {
            listVocabularyItem.shuffle()
            val voca = listVocabularyItem[0]
            listVocabularyItem.remove(voca)

            val listChar = mutableListOf<WordChar>()
            tvVi.text = voca.vietnameseWordItem
            voca.englishWordItem.forEach { it ->
                listChar.add(WordChar().apply {
                    text = it.toString()
                    isShow = true
                    index = -1
                })
            }
            listChar.shuffle()
            adapterVocaTest.setData(listChar)
        }
    }
}