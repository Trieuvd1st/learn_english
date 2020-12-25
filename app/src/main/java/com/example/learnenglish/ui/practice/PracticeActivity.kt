package com.example.learnenglish.ui.practice

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.learnenglish.R
import com.example.learnenglish.model.ToeicListResponse
import com.example.learnenglish.model.ToeicPartType
import com.example.learnenglish.model.ToeicSentence
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.communication.commtest.CommTestViewModel
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_practice.*

class PracticeActivity : BaseActivity() {

    private lateinit var viewModelPractice: PracticeViewModel

    private val TOEIC = "toiec"
    private val exam = "exam_1"
    private lateinit var storageRef: StorageReference
    private var audio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        storageRef = FirebaseStorage.getInstance().reference.child(TOEIC).child(exam)

        viewModelPractice = ViewModelProviders.of(this).get(PracticeViewModel::class.java).apply {
            toeicListResponse.observe(this@PracticeActivity, Observer { result ->
                handleDataResponse(result)
            })
        }

        viewModelPractice.getData()

        ivSpeaker.setOnClickListener {
            playAudio(audio)
        }

        btnNext.setOnClickListener {

        }
    }

    private fun handleDataResponse(toeicListResponse: ToeicListResponse) {
        showPart1(toeicListResponse.part1)
    }

    private fun showPart1(part1: MutableList<ToeicSentence>?) {
        setViewType(ToeicPartType.PART_1)
        part1?.get(0)?.let {
            audio = it.audio!!
            playAudio(audio)
            loadImage(it.imagePath!!)
        }
    }

    private fun playAudio(urlKey: String) {
        storageRef.child(urlKey).downloadUrl.addOnSuccessListener {
            val player = MediaPlayer()
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(it.toString())
            player.prepare()
            player.start()
        }.addOnFailureListener { }
    }

    private fun loadImage(urlKey: String) {
        storageRef.child(urlKey).downloadUrl.addOnSuccessListener {
            Glide.with(this).load(it.toString()).into(imagePart)
        }.addOnFailureListener { }
    }

    private fun setViewType(toeicPartType: ToeicPartType) {
        when (toeicPartType) {
            ToeicPartType.PART_1 -> {
                ivSpeaker.visibility = View.VISIBLE
                imagePart.visibility = View.VISIBLE
                tvSentence1.visibility = View.GONE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.GONE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.GONE
                mcvChoice2.visibility = View.GONE
                tvQuestion3.visibility = View.GONE
                mcvChoice3.visibility = View.GONE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }

            ToeicPartType.PART_2 -> {
                ivSpeaker.visibility = View.VISIBLE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.GONE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.VISIBLE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.GONE
                mcvChoice2.visibility = View.GONE
                tvQuestion3.visibility = View.GONE
                mcvChoice3.visibility = View.GONE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }

            ToeicPartType.PART_3 -> {
                ivSpeaker.visibility = View.VISIBLE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.GONE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.VISIBLE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.VISIBLE
                mcvChoice2.visibility = View.VISIBLE
                tvQuestion3.visibility = View.VISIBLE
                mcvChoice3.visibility = View.VISIBLE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }

            ToeicPartType.PART_4 -> {
                ivSpeaker.visibility = View.VISIBLE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.GONE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.VISIBLE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.VISIBLE
                mcvChoice2.visibility = View.VISIBLE
                tvQuestion3.visibility = View.VISIBLE
                mcvChoice3.visibility = View.VISIBLE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }

            ToeicPartType.PART_5 -> {
                ivSpeaker.visibility = View.GONE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.GONE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.VISIBLE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.GONE
                mcvChoice2.visibility = View.GONE
                tvQuestion3.visibility = View.GONE
                mcvChoice3.visibility = View.GONE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }

            ToeicPartType.PART_6 -> {
                ivSpeaker.visibility = View.GONE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.VISIBLE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.GONE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.GONE
                mcvChoice2.visibility = View.VISIBLE
                tvQuestion3.visibility = View.GONE
                mcvChoice3.visibility = View.VISIBLE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.VISIBLE
            }

            ToeicPartType.PART_7 -> {
                ivSpeaker.visibility = View.GONE
                imagePart.visibility = View.GONE
                tvSentence1.visibility = View.VISIBLE
                tvSentence2.visibility = View.GONE
                tvQuestion1.visibility = View.VISIBLE
                mcvChoice1.visibility = View.VISIBLE
                tvQuestion2.visibility = View.VISIBLE
                mcvChoice2.visibility = View.VISIBLE
                tvQuestion3.visibility = View.VISIBLE
                mcvChoice3.visibility = View.VISIBLE
                tvQuestion4.visibility = View.GONE
                mcvChoice4.visibility = View.GONE
            }
        }
    }

    companion object {

    }
}
