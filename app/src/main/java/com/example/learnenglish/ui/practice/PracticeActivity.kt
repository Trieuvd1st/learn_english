package com.example.learnenglish.ui.practice

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import com.example.learnenglish.R
import com.example.learnenglish.ui.base.BaseActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class PracticeActivity : BaseActivity() {

    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        storageRef = FirebaseStorage.getInstance().reference
        val music = storageRef.child("areYouGoingToAttendTheirWedding.mp3")
        music.downloadUrl.addOnSuccessListener {
            val player = MediaPlayer()
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(it.toString())
            player.prepare()
            player.start()
        }.addOnFailureListener { }
        /*val player = MediaPlayer()
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        player.setDataSource(music.name)
        player.prepare()
        player.start()*/
    }
}