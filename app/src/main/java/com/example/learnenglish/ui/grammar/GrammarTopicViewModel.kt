package com.example.learnenglish.ui.grammar

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class GrammarTopicViewModel: ViewModel() {

    fun unlockTopic(topicId: Int) {
        val userId = Firebase.auth.currentUser?.uid
        FirebaseDatabase.getInstance().reference.child("grammar_unlock").child(userId + topicId)
            .child("userId").setValue(userId)
        FirebaseDatabase.getInstance().reference.child("grammar_unlock").child(userId + topicId)
            .child("topicId").setValue(topicId)
    }
}