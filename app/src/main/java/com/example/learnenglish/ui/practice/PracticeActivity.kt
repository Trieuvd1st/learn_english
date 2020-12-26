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
import com.example.learnenglish.model.*
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.communication.commtest.CommTestViewModel
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_practice.*
import java.lang.Exception

class PracticeActivity : BaseActivity() {

    private lateinit var viewModelPractice: PracticeViewModel

    private val TOEIC = "toeic"
    private val EXAM = "exam_1"
    private lateinit var storageRef: StorageReference
    private var audio = ""
    private lateinit var player: MediaPlayer
    private var partNumber = 1
    private var listToeicSen = ToeicListResponse()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        storageRef = FirebaseStorage.getInstance().reference.child(TOEIC).child(EXAM)
        player = MediaPlayer()
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)

        viewModelPractice = ViewModelProviders.of(this).get(PracticeViewModel::class.java).apply {
            toeicListResponse.observe(this@PracticeActivity, Observer { result ->
                Log.d("DATA_GET", result.part1?.get(0)?.audio!!)
                handleDataResponse(result)
            })
        }

        ivSpeaker.setOnClickListener {
            playAudio(audio)
        }

        btnNext.setOnClickListener {
            onCLickBtnNext()
        }
    }

    private fun handleDataResponse(toeicListResponse: ToeicListResponse) {
        listToeicSen = toeicListResponse
        onCLickBtnNext()
    }

    private fun onCLickBtnNext() {
        player.reset()
        when (partNumber) {
            1 -> {
                showPart1(listToeicSen.part1?.get(index++))
                if (index == listToeicSen.part1?.size!!) {
                    partNumber = 2
                    index = 0
                }
            }

            2 -> {
                showPart2(listToeicSen.part2?.get(index++))
                if (index == listToeicSen.part2?.size!!) {
                    partNumber = 3
                    index = 0
                }
            }

            3 -> {
                showPart3(listToeicSen.part3?.get(index++)!!)
                if (index == listToeicSen.part3?.size!!) {
                    partNumber = 4
                    index = 0
                }
            }

            4 -> {
                showPart4(listToeicSen.part4?.get(index++)!!)
                if (index == listToeicSen.part4?.size!!) {
                    partNumber = 5
                    index = 0
                }
            }

            5 -> {
                showPart5(listToeicSen.part5?.get(index++))
                if (index == listToeicSen.part5?.size) {
                    partNumber = 6
                    index = 0
                }
            }

            6 -> {
                showPart6(listToeicSen.part6?.get(index++)!!)
                if (index == listToeicSen.part6?.size!!) {
                    partNumber = 7
                    index = 0
                }
            }

            7 -> {
                showPart7(listToeicSen.part7?.get(index++)!!)
                if (index == listToeicSen.part7?.size!!) {
                    partNumber = 1
                    index = 0
                }
            }
        }
    }

    private fun showPart1(toeicSentence: ToeicSentence?) {
        setViewType(ToeicPartType.PART_1)
        toeicSentence?.let {
            audio = it.audio!!
            playAudio(audio)
            loadImage(it.imagePath!!)
        }
    }

    private fun showPart2(toeicSentence: ToeicSentence?) {
        setViewType(ToeicPartType.PART_2)
        toeicSentence?.let {
            audio = it.audio!!
            playAudio(audio)
            tvQuestion1.text = "Mark your answer on your answer sheet."
        }
    }

    private fun showPart3(part3: Part3) {
        setViewType(ToeicPartType.PART_3)
        part3.let {
            audio = it.audio!!
            playAudio(audio)
            it.listSen?.let { that ->
                tvQuestion1.text = that[0].question
                mcvChoice1.setChoice1(that[0].choice_1.toString())
                mcvChoice1.setChoice2(that[0].choice_2.toString())
                mcvChoice1.setChoice3(that[0].choice_3.toString())
                mcvChoice1.setChoice4(that[0].choice_4.toString())

                tvQuestion2.text = that[1].question
                mcvChoice2.setChoice1(that[1].choice_1.toString())
                mcvChoice2.setChoice2(that[1].choice_2.toString())
                mcvChoice2.setChoice3(that[1].choice_3.toString())
                mcvChoice2.setChoice4(that[1].choice_4.toString())

                tvQuestion3.text = that[2].question
                mcvChoice3.setChoice1(that[2].choice_1.toString())
                mcvChoice3.setChoice2(that[2].choice_2.toString())
                mcvChoice3.setChoice3(that[2].choice_3.toString())
                mcvChoice3.setChoice4(that[2].choice_4.toString())
            }
        }
    }

    private fun showPart4(part4: Part4) {
        setViewType(ToeicPartType.PART_4)
        part4.let {
            audio = it.audio!!
            playAudio(audio)
            it.listSen?.let { that ->
                tvQuestion1.text = that[0].question
                mcvChoice1.setChoice1(that[0].choice_1.toString())
                mcvChoice1.setChoice2(that[0].choice_2.toString())
                mcvChoice1.setChoice3(that[0].choice_3.toString())
                mcvChoice1.setChoice4(that[0].choice_4.toString())

                tvQuestion2.text = that[1].question
                mcvChoice2.setChoice1(that[1].choice_1.toString())
                mcvChoice2.setChoice2(that[1].choice_2.toString())
                mcvChoice2.setChoice3(that[1].choice_3.toString())
                mcvChoice2.setChoice4(that[1].choice_4.toString())

                tvQuestion3.text = that[2].question
                mcvChoice3.setChoice1(that[2].choice_1.toString())
                mcvChoice3.setChoice2(that[2].choice_2.toString())
                mcvChoice3.setChoice3(that[2].choice_3.toString())
                mcvChoice3.setChoice4(that[2].choice_4.toString())
            }
        }
    }

    private fun showPart5(toeicSentence: ToeicSentence?) {
        setViewType(ToeicPartType.PART_5)
        toeicSentence?.let {
            tvQuestion1.text = it.question
            mcvChoice1.setChoice1(it.choice_1.toString())
            mcvChoice1.setChoice2(it.choice_2.toString())
            mcvChoice1.setChoice3(it.choice_3.toString())
            mcvChoice1.setChoice4(it.choice_4.toString())
        }
    }

    private fun showPart6(part6: Part6) {
        setViewType(ToeicPartType.PART_6)
        part6.let {
            tvSentence1.text = it.text
            it.listSen?.let { that ->
                mcvChoice1.setChoice1(that[0].choice_1.toString())
                mcvChoice1.setChoice2(that[0].choice_2.toString())
                mcvChoice1.setChoice3(that[0].choice_3.toString())
                mcvChoice1.setChoice4(that[0].choice_4.toString())

                mcvChoice2.setChoice1(that[1].choice_1.toString())
                mcvChoice2.setChoice2(that[1].choice_2.toString())
                mcvChoice2.setChoice3(that[1].choice_3.toString())
                mcvChoice2.setChoice4(that[1].choice_4.toString())

                mcvChoice3.setChoice1(that[2].choice_1.toString())
                mcvChoice3.setChoice2(that[2].choice_2.toString())
                mcvChoice3.setChoice3(that[2].choice_3.toString())
                mcvChoice3.setChoice4(that[2].choice_4.toString())
            }

            if (part6.listSen?.size == 4) {
                mcvChoice4.visibility = View.VISIBLE
                mcvChoice4.setChoice1(it.listSen?.get(3)?.choice_1.toString())
                mcvChoice4.setChoice2(it.listSen?.get(3)?.choice_2.toString())
                mcvChoice4.setChoice3(it.listSen?.get(3)?.choice_3.toString())
                mcvChoice4.setChoice4(it.listSen?.get(3)?.choice_4.toString())
            } else {
                mcvChoice4.visibility = View.GONE
            }
        }
    }

    private fun showPart7(part7: Part7) {
        setViewType(ToeicPartType.PART_7)
        part7.let {
            tvSentence1.text = it.text_1
            if (it.textNumber?.toInt() == 2) {
                tvSentence2.visibility = View.VISIBLE
                tvSentence2.text = it.text_2
            }
            it.listSen?.let { that ->
                tvQuestion1.text = that[0].question
                mcvChoice1.setChoice1(that[0].choice_1.toString())
                mcvChoice1.setChoice2(that[0].choice_2.toString())
                mcvChoice1.setChoice3(that[0].choice_3.toString())
                mcvChoice1.setChoice4(that[0].choice_4.toString())

                tvQuestion2.text = that[1].question
                mcvChoice2.setChoice1(that[1].choice_1.toString())
                mcvChoice2.setChoice2(that[1].choice_2.toString())
                mcvChoice2.setChoice3(that[1].choice_3.toString())
                mcvChoice2.setChoice4(that[1].choice_4.toString())

                tvQuestion3.text = that[2].question
                mcvChoice3.setChoice1(that[2].choice_1.toString())
                mcvChoice3.setChoice2(that[2].choice_2.toString())
                mcvChoice3.setChoice3(that[2].choice_3.toString())
                mcvChoice3.setChoice4(that[2].choice_4.toString())
            }

            if (part7.listSen?.size == 4) {
                mcvChoice4.visibility = View.VISIBLE
                tvQuestion4.text = it.listSen?.get(3)?.question
                mcvChoice4.setChoice1(it.listSen?.get(3)?.choice_1.toString())
                mcvChoice4.setChoice2(it.listSen?.get(3)?.choice_2.toString())
                mcvChoice4.setChoice3(it.listSen?.get(3)?.choice_3.toString())
                mcvChoice4.setChoice4(it.listSen?.get(3)?.choice_4.toString())
            } else {
                mcvChoice4.visibility = View.GONE
            }
        }
    }

    private fun playAudio(urlKey: String) {
        storageRef.child("$urlKey.mp3").downloadUrl.addOnSuccessListener {
            Log.d("AUDIO_PATH: ", "$it")
            try {
                player.setDataSource(it.toString())
                player.prepare()
                player.start()
            } catch (e: Exception) { }
        }.addOnFailureListener {
            Log.d("AUDIO_PATH_ERROR:", "${it}")
        }
    }

    private fun loadImage(urlKey: String) {
        storageRef.child("$urlKey.png").downloadUrl.addOnSuccessListener {
            Log.d("IMAGE_PATH", "$it")
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
                mcvChoice1.set4Choice(false)
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

    override fun onStop() {
        super.onStop()
        if (player.isPlaying) player.release()
    }
}
