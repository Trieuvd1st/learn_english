package com.example.learnenglish.ui.vocabulary.topic

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
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
                Log.d("DOWNLOAD_STATE", "${fileRef.getBytes(1024 * 1024).result}")
                val outFile = File("${context.getExternalFilesDir(null)}${File.separator}${fileRef.name}")
                try {
                    val fos = FileOutputStream(outFile)
                    fos.write(fileRef.getBytes(1024 * 1024).result)
                    fos.close()
                    Log.d("SAVE_FILE", "SUCESS")
                } catch (e: Exception) {
                    Log.d("SAVE_FILE:", "${e.message.toString()}")
                }

            }
        }.addOnFailureListener {
            Log.d("DOWNLOAD_STATE", "${it.message.toString()}")
        }
    }
}