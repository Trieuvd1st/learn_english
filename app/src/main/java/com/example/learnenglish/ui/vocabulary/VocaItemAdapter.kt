package com.example.learnenglish.ui.vocabulary

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.vocabulary.detail.DetailVocaActivity
import kotlinx.android.synthetic.main.stream_item_vocabulary.view.*
import java.io.IOException
import java.io.InputStream

class VocaItemAdapter(private var listVocaItem: MutableList<VocabularyItem>) :
    RecyclerView.Adapter<VocaItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaItemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.stream_item_vocabulary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VocaItemAdapter.ViewHolder, position: Int) {
        holder.bind(listVocaItem[position])
    }

    override fun getItemCount(): Int {
        return listVocaItem.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vocaItem: VocabularyItem) = with(itemView) {
            var imss: InputStream? = null
            try {
                imss = context.assets.open("img/" + vocaItem.imageItem + ".jpg")
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val d = Drawable.createFromStream(imss, null)
            image.setImageDrawable(d)
            tvSpell.text = vocaItem.spell
            tvEn.text = vocaItem.englishWordItem
            tvVi.text = vocaItem.vietnameseWordItem
            btnSpeaker.setOnClickListener {
                MediaPlayer.create(
                    context,
                    resources.getIdentifier(vocaItem.soundItem, "raw", context.packageName)
                ).start()
            }
            itemView.setOnClickListener {
                context.startActivity(Intent(context, DetailVocaActivity::class.java).apply {
                    putExtra("EXTRA_ITEM_VOCA_TO_DETAIL", vocaItem)
                })
            }
        }
    }

}