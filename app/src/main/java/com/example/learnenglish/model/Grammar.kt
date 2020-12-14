package com.example.learnenglish.model

import java.io.Serializable

class Grammar(
        var id: Int = 0,
        var topic: String = "",
        var detailContent: String = ""
) : Serializable