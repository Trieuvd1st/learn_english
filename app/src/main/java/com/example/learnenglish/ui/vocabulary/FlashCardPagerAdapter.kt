package com.example.learnenglish.ui.vocabulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.learnenglish.model.VocabularyItem

class FlashCardPagerAdapter(fm: FragmentManager, val listVocabularyItem: List<VocabularyItem>) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return listVocabularyItem.size
    }

    override fun getItem(position: Int): Fragment {
        return FlashCardItemFragment().apply {
            arguments = Bundle().apply {
                putSerializable(FlashCardItemFragment.EXTRA_VOCABULARY_ITEM, listVocabularyItem[position])
            }
        }
    }

}