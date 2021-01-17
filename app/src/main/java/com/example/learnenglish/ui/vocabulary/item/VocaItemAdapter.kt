package com.example.learnenglish.ui.vocabulary.item

import android.R.attr.path
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
import java.io.File
import java.io.IOException
import java.io.InputStream


class VocaItemAdapter(private var listVocaItem: MutableList<VocabularyItem>) :
    RecyclerView.Adapter<VocaItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.stream_item_vocabulary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
                val d = Drawable.createFromStream(imss, null)
                //image.setImageDrawable(d)
                image.setImageDrawable(Drawable.createFromPath("${context.getExternalFilesDir(null)}${File.separator}${"100.jpg"}"))
                if (vocaItem.idItem < 8) {

                } else {

                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            tvSpell.text = vocaItem.spell
            tvEn.text = vocaItem.englishWordItem
            tvVi.text = vocaItem.vietnameseWordItem

            btnSpeaker.setOnClickListener {
                val player = MediaPlayer()
                try {
                    player.setDataSource("${context.getExternalFilesDir(null)}${File.separator}${"cold.mp3"}")
                    player.prepare()
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    println("Exception of type : $e")
                    e.printStackTrace()
                }
                player.start()
                /*MediaPlayer.create(
                    context,
                    resources.getIdentifier(vocaItem.soundItem, "raw", context.packageName)
                ).start()*/
            }
            itemView.setOnClickListener {
                context.startActivity(Intent(context, DetailVocaActivity::class.java).apply {
                    putExtra("EXTRA_ITEM_VOCA_TO_DETAIL", vocaItem)
                })
            }
        }
    }

}