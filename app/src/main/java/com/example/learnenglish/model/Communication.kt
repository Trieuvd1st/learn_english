package com.example.learnenglish.model

import java.io.Serializable

class Communication : Serializable {
    var id: Int = 0

    var topicId: Int = 0

    var enSentence: String = ""

    var viSentence: String = ""

    var nameSound: String = ""

    var isExpand: Boolean = false

    fun convertSelection(value: Int): Boolean {
        return when (value) {
            0 -> false
            else -> true
        }
    }
}