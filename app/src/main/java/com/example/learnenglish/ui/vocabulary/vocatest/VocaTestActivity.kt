package com.example.learnenglish.ui.vocabulary.vocatest

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.database.VocabularyItemDatabase
import com.example.learnenglish.model.Vocabulary
import com.example.learnenglish.model.WordChar
import com.example.learnenglish.widgets.AnswerTestDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_voca_test.*

class VocaTestActivity : AppCompatActivity() {

    private lateinit var adapterVocaTest: VocaTestAdapter
    private var enResult: String = ""
    private var listVocabularyItem = mutableListOf<Vocabulary>()
    private var totalItem = 0
    private var currentVoca = Vocabulary()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_test)

        val topicId = intent.getIntExtra("VOCA_ITEM_PRATICE", 0)

        val vocabularyItemDatabase = VocabularyItemDatabase(this)
        listVocabularyItem = vocabularyItemDatabase.getListVocabularyItem(topicId)
        totalItem = listVocabularyItem.size
        pbCountNumber.max = totalItem

        initAdapter()
        showTest()

        ivBack.setOnClickListener {
            finish()
        }
        ivSkip.setOnClickListener {
            showTest()
        }

        ivSpeaker.setOnClickListener {
            MediaPlayer.create(
                this,
                resources.getIdentifier(currentVoca.soundId, "raw", packageName)
            ).start()
        }

        btnCheck.setOnClickListener {
            AnswerTestDialog(
                this,
                tvEnResult.text.toString()
                    .equals(currentVoca.enWord.toString(), ignoreCase = true),
                currentVoca.enWord,
                object : AnswerTestDialog.VoCaAnswerTestDialogListener {
                    override fun onBtnNext() {
                        showTest()
                    }

                }).show()
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
            currentVoca = listVocabularyItem[0]
            listVocabularyItem.remove(currentVoca)

            val listChar = mutableListOf<WordChar>()
            tvVi.text = currentVoca.viWord
            currentVoca.enWord.forEach { it ->
                listChar.add(WordChar().apply {
                    text = it.toString()
                    isShow = true
                    index = -1
                })
            }
            listChar.shuffle()
            adapterVocaTest.setData(listChar)
            pbCountNumber.progress = totalItem - listVocabularyItem.size
        }
    }

    override fun onStop() {
        super.onStop()
        if (Firebase.auth.currentUser?.isAnonymous != null) {
            Log.d("TRIEUVD", "${UserManager.getMyPoint(this)}")
            FirebaseDatabase.getInstance().reference.child("users")
                .child(Firebase.auth.currentUser?.uid!!).child("myPoint")
                .setValue(UserManager.getMyPoint(this))
        }
    }
}