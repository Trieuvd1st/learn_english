package com.example.learnenglish.ui.authentication.signin

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInViewModel: ViewModel() {

    var loginState = MutableLiveData<Boolean>()
    var messageError = MutableLiveData<String>()
    var isShowLoading = MutableLiveData<Boolean>()

    fun signIn(activity: Activity, email: String, password: String) {
        isShowLoading.value = true
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                isShowLoading.value = false
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    loginState.value = true
                } else {
                    loginState.value = false
                    messageError.value = task.exception.toString()
                }
            }

    }
}