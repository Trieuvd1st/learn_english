package com.example.learnenglish.ui.authentication.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.ui.authentication.signup.SignUpActivity
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.communication.commtest.CommTestViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity: BaseActivity() {

    private lateinit var viewmodel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        Firebase.auth.signOut()

        
        btnSignIn.setOnClickListener { 
            viewmodel.signIn(this, "ductrieu98htrung@gmail.com", "morhn123")
        }
        
        btnSignUp.setOnClickListener {
            Log.d("sign_out", "${Firebase.auth.currentUser?.isAnonymous}")
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        viewmodel = ViewModelProviders.of(this).get(SignInViewModel::class.java).apply {
            loginState.observe(this@SignInActivity, Observer { state ->
                if (state) finish()
            })
            messageError.observe(this@SignInActivity, Observer { msg ->
                Toast.makeText(this@SignInActivity, msg, Toast.LENGTH_SHORT).show()
            })

            isShowLoading.observe(this@SignInActivity, Observer { state ->
                showOrHideProgressDialog(state)
            })
        }
    }
}