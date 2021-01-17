package com.example.learnenglish.ui.vocabulary.topic

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.learnenglish.database.UserManager
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception


class VocalbularyViewModel : ViewModel() {

    fun getVocabularyItem() {

    }

    fun downloadItemVoca(context: Context, id: Int) {
        FirebaseStorage.getInstance().reference.child("voca_item").child("$id").listAll().addOnSuccessListener { result ->
            for (fileRef in result.items) {
                Log.d("DOWNLOAD_STATE", "${context.getExternalFilesDir(null)}${File.separator}${fileRef.name}")
                val localFile = File("${context.getExternalFilesDir(null)}${File.separator}${fileRef.name}")
                fileRef.getFile(localFile).addOnSuccessListener {

                }
            }

        }.addOnFailureListener {
            Log.d("DOWNLOAD_STATE", "${it.message.toString()}")
        }
    }

    fun unlockTopic(topicId: Int) {
        val userId = Firebase.auth.currentUser?.uid
        FirebaseDatabase.getInstance().reference.child("vocabulary_unlock").child(userId + topicId)
            .child("userId").setValue(userId)
        FirebaseDatabase.getInstance().reference.child("vocabulary_unlock").child(userId + topicId)
            .child("topicId").setValue(topicId)
    }
}