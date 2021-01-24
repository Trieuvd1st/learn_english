package com.example.learnenglish.ui.authentication.signin

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.database.VocabularyDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class SignInViewModel: ViewModel() {

    var loginState = MutableLiveData<Boolean>()
    var messageError = MutableLiveData<String>()
    var isShowLoading = MutableLiveData<Boolean>()
    var vocabularyDatabase: VocabularyDatabase? = null
    var grammarDatabase: GrammarDatabase? = null
    var isGetData: Boolean = false

    fun signIn(activity: Activity, email: String, password: String) {
        isShowLoading.value = true
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Firebase.auth.uid?.let { getMyPoint(activity, it) }
                } else {
                    isShowLoading.value = false
                    messageError.value = task.exception?.message.toString()
                }
            }
    }

    private fun getMyPoint(context: Context, uid: String) {
        FirebaseDatabase.getInstance().reference.child("users").child(uid).child("myPoint").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isShowLoading.value = false
                loginState.value = true
                Log.d("MY_POINT", "${snapshot.value.toString().toInt()}")
                UserManager.setMyPoint(context, snapshot.value.toString().toInt())
            }

            override fun onCancelled(error: DatabaseError) {  }

        })
    }

    fun checkUnlockTopic(context: Context) {
        isGetData = true
        vocabularyDatabase = VocabularyDatabase(context)
        grammarDatabase = GrammarDatabase(context)
        //unlock vocatopic
        FirebaseDatabase.getInstance().reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (isGetData) {
                    for (item in snapshot.child("vocabulary_unlock").children) {
                        if (item.child("userId").value == Firebase.auth.currentUser?.uid!!) {
                            vocabularyDatabase?.updatePointById((item.child("topicId").value as Long).toInt())
                        }
                    }

                    for (item in snapshot.child("grammar_unlock").children) {
                        if (item.child("userId").value == Firebase.auth.currentUser?.uid!!) {
                            grammarDatabase?.updatePointById((item.child("topicId").value as Long).toInt())
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}