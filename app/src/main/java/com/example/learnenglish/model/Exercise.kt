package com.example.learnenglish.model

import java.io.Serializable

class Exercise (
    var id: Int = 0,

    var question: String = "",

    var ansA: String = "",

    var ansB: String = "",

    var ansC: String = "",

    var ansD: String = "",

    var correctAns: Int = 0
)