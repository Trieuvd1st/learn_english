package com.example.learnenglish.ui.authentication.signin

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnenglish.database.UserManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class SignInViewModel: ViewModel() {

    var loginState = MutableLiveData<Boolean>()
    var messageError = MutableLiveData<String>()
    var isShowLoading = MutableLiveData<Boolean>()

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
}