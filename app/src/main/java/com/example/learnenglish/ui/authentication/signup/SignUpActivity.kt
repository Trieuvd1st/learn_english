package com.example.learnenglish.ui.authentication.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.learnenglish.R
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.main.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.btnSignUp
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp.setOnClickListener {
            signUp(edtEmail.text.toString(), edtPassword.text.toString())
        }
    }

    private fun signUp(email: String, password: String) {
        showOrHideProgressDialog(true)
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                showOrHideProgressDialog(false)
                if (task.isSuccessful) {
                    FirebaseDatabase.getInstance().reference.child("users").child(Firebase.auth.currentUser?.uid!!).child("myPoint").setValue(200.toLong())
                    UserManager.setMyPoint(this, 200)
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                } else {
                    Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }
}