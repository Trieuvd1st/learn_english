package com.example.learnenglish.model

class ToeicListResponse {
    var part1: MutableList<ToeicSentence>? = mutableListOf()
    var part2: MutableList<ToeicSentence>? = mutableListOf()
    var part3: MutableList<Part3>? = mutableListOf()
    var part4: MutableList<Part4>? = mutableListOf()
    var part5: MutableList<ToeicSentence>? = mutableListOf()
    var part6: MutableList<Part6>? = mutableListOf()
    var part7: MutableList<Part7>? = mutableListOf()
    var time: Long? = 0
}