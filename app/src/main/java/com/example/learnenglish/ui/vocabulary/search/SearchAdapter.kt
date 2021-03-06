package com.example.learnenglish.ui.vocabulary.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.vocabulary.detail.DetailVocaActivity
import kotlinx.android.synthetic.main.item_voca_search.view.*

class SearchAdapter(private var listSearchResult: MutableList<VocabularyItem>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_voca_search, parent, false)
        )
    }

    fun setData(listSearchResult: MutableList<VocabularyItem>) {
        this.listSearchResult.clear()
        this.listSearchResult.addAll(listSearchResult)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSearchResult[position])
    }

    override fun getItemCount(): Int {
        return listSearchResult.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vocaItem: VocabularyItem) = with(itemView) {
            tvEn.text = vocaItem.englishWordItem
            tvVi.text = vocaItem.vietnameseWordItem
            itemView.setOnClickListener {
                context.startActivity(Intent(context, DetailVocaActivity::class.java).apply {
                    putExtra("EXTRA_ITEM_VOCA_TO_DETAIL", vocaItem)
                })
            }
        }
    }

}