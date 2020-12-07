package com.example.learnenglish.model

import java.io.Serializable

class CommTopic(
        var id: Int = 0,

        var topicName: String = "",

        var imageTopic: String? = ""
) : Serializable