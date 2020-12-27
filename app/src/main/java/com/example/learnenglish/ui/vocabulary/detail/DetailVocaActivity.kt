package com.example.learnenglish.ui.vocabulary.detail

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.model.VocabularyItem
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.utils.DisplayUtils
import kotlinx.android.synthetic.main.activity_detail_voca.*
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.stream_item_vocabulary.view.*
import java.io.IOException
import java.io.InputStream

class DetailVocaActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_voca)

        val vocaItem: VocabularyItem = intent.getSerializableExtra("EXTRA_ITEM_VOCA_TO_DETAIL") as VocabularyItem

        setTitleActionBar(toolbar, vocaItem.englishWordItem)

        var imss: InputStream? = null
        try {
            imss = assets.open("img/" + vocaItem.imageItem + ".jpg")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val d = Drawable.createFromStream(imss, null)
        image.setImageDrawable(d)
        tvEn.text = vocaItem.englishWordItem
        vocaItem.spell?.let { tvSpell.text = vocaItem.spell }
        tvVi.text = vocaItem.vietnameseWordItem
        vocaItem.example?.let {
            tvExample.text = DisplayUtils.fromHtml(it)
        }

        btnSpeaker.setOnClickListener {
            MediaPlayer.create(
                this,
                resources.getIdentifier(vocaItem.soundItem, "raw", packageName)
            ).start()
        }
    }
}