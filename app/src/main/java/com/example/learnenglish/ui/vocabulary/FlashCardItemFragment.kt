package com.example.learnenglish.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.learnenglish.R
import com.example.learnenglish.model.VocabularyItem
import kotlinx.android.synthetic.main.fragment_flash_card_item.*

class FlashCardItemFragment : Fragment() {

    private lateinit var vocabularyItem: VocabularyItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flash_card_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSeeVi.setOnClickListener {
            tvVi.isVisible = true
        }

        vocabularyItem = arguments?.getSerializable(EXTRA_VOCABULARY_ITEM) as VocabularyItem
        tvEn.text = vocabularyItem.englishWordItem
        tvVi.text = vocabularyItem.vietnameseWordItem
    }

    companion object {
        const val EXTRA_VOCABULARY_ITEM = "com.example.learnenglish.ui.vocabulary.EXTRA_VOCABULARY_ITEM"
    }
}