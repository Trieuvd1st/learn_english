package com.example.learnenglish.ui.vocabulary.detail

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import com.example.learnenglish.R
import com.example.learnenglish.model.Vocabulary
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.utils.DisplayUtils
import kotlinx.android.synthetic.main.activity_detail_voca.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.io.IOException
import java.io.InputStream

class DetailVocaActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_voca)

        val vocaItem: Vocabulary = intent.getSerializableExtra("EXTRA_ITEM_VOCA_TO_DETAIL") as Vocabulary

        setTitleActionBar(toolbar, vocaItem.enWord)

        var imss: InputStream? = null
        try {
            imss = assets.open("img/" + vocaItem.imageId + ".jpg")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val d = Drawable.createFromStream(imss, null)
        image.setImageDrawable(d)
        tvEn.text = vocaItem.enWord
        vocaItem.spell?.let { tvSpell.text = vocaItem.spell }
        tvVi.text = vocaItem.viWord
        vocaItem.example?.let {
            tvExample.text = DisplayUtils.fromHtml(it)
        }

        btnSpeaker.setOnClickListener {
            MediaPlayer.create(
                this,
                resources.getIdentifier(vocaItem.soundId, "raw", packageName)
            ).start()
        }
    }
}