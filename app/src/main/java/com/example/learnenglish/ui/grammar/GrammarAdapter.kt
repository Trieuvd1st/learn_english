package com.example.learnenglish.ui.grammar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.Grammar
import kotlinx.android.synthetic.main.item_grammar.view.*

class GrammarAdapter(private var listGrammar: MutableList<Grammar>) :
        RecyclerView.Adapter<GrammarAdapter.ViewHolder>() {

    private var listener: GrammarAdapterListener? = null

    fun setListener(listener: GrammarAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_grammar,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listGrammar.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGrammar[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(grammar: Grammar) = with(itemView) {
            tvId.text = grammar.id.toString()
            tvTopic.text = grammar.topic
            itemView.setOnClickListener {
                GrammarContentActivity.startNewActivity(context, grammar)
            }

            ivPointRequire.isVisible = grammar.pointRequired > 0

            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface GrammarAdapterListener {
        fun onItemClick(pos: Int)
    }

}