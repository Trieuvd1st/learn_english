package com.example.learnenglish.ui.vocabulary.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.WordChar
import kotlinx.android.synthetic.main.item_word.view.*

enum class VoCaTestItemType(val type: Int) {
    HEADER(0),
    ITEM(1)
}

class VocaTestAdapter(private var listChar: MutableList<WordChar>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: VocaTestAdapterListener? = null
    var isEnableBtnback: Boolean = false

    interface VocaTestAdapterListener {
        fun onHeaderClick()
        fun onItemClick(wordChar: WordChar, pos: Int)
    }

    fun setListener(listener: VocaTestAdapterListener) {
        this.listener = listener
    }

    fun setData(listChar: List<WordChar>) {
        this.listChar.clear()
        this.listChar.addAll(listChar)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VoCaTestItemType.HEADER.type -> {
                return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header_word, parent, false))
            }

            else -> {
                ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("TRIEUVD on itemcount", "${listChar.size}")
        return listChar.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VoCaTestItemType.HEADER.type
        } else VoCaTestItemType.ITEM.type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as HeaderViewHolder).bind(isEnableBtnback)
        } else {
            (holder as ItemViewHolder).bind(listChar[position - 1], position - 1)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wordChar: WordChar, pos: Int) = with(itemView) {
            tvChar.text = wordChar.text

            if (wordChar.isShow) {
                itemView.visibility = View.VISIBLE
            } else {
                itemView.visibility = View.INVISIBLE
            }

            itemView.setOnClickListener {
                listener?.onItemClick(wordChar, pos)
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(isBtnBackEnable: Boolean = false) {

            itemView.isEnabled = isBtnBackEnable

            itemView.setOnClickListener {
                listener?.onHeaderClick()
            }
        }
    }

    fun showChar(lastChar: Int) {
        for (i in 0 until listChar.size) {
            if (listChar[i].index == lastChar) {
                listChar[i].isShow = true
                listChar[i].index = -1
            }
        }
        notifyDataSetChanged()

    }

    fun hideChar(pos: Int, index: Int) {
        listChar[pos].isShow = false
        listChar[pos].index = index
        notifyDataSetChanged()
    }


}