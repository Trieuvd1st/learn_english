package com.example.learnenglish.ui.authentication.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnenglish.R
import com.example.learnenglish.database.DBHelper
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.main.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ProfileActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setTitleActionBar(toolbar, "Thông tin cá nhân")

        if (Firebase.auth.currentUser?.isAnonymous == false) {
            tvMail.text = Firebase.auth.currentUser?.email
            tvMyPoint.text = UserManager.getMyPoint(this).toString()
        }

        val dbHelper = DBHelper(this)
        tvSignOut.setOnClickListener {
            Firebase.auth.signOut()
            UserManager.clearData(this)
            dbHelper.deleteDatabase(this)
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}