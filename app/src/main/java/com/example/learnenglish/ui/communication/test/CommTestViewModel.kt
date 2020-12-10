package com.example.learnenglish.ui.communication.test

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnenglish.database.CommunicationDatabase
import com.example.learnenglish.model.Communication
import kotlin.random.Random

class CommTestViewModel : ViewModel() {

    private lateinit var commDatabase: CommunicationDatabase
    private var listCommSelected: ArrayList<Communication>? = null
    var commAnswer: Communication = Communication()
    var indexAnswer: Int = -1
    var choicePiked: String = ""

    val listSize = MutableLiveData<Int>()
    val commAnswerData = MutableLiveData<Communication>()
    val listChoiceData = MutableLiveData<ArrayList<Communication>>()
    val isAnswer = MutableLiveData<Boolean>()
    val soundAnswerData = MutableLiveData<String>()

    fun getListComm(context: Context): ArrayList<Communication>? {
        commDatabase = CommunicationDatabase(context)
        return commDatabase.getListComm()
    }

    fun getListCommSelected(context: Context) {
        commDatabase = CommunicationDatabase(context)
        listCommSelected = commDatabase.getListComm()
    }

    fun getRandomComm(): Communication {
        var commRandom = Communication()
        listCommSelected?.let {
            if (it.size > 0) {
                indexAnswer = Random.nextInt(0, it.size)
                commRandom = it[indexAnswer]
                listCommSelected?.remove(commRandom)
                commAnswerData.value = commRandom
            }
        }
        Log.d("TRIEUVD", "listCommSelected: ${listCommSelected?.size}")
        return commRandom
    }

    fun setChoicePicked(choicePiked: String) {
        this.choicePiked = choicePiked
    }

    fun getRandomMutileChoice(context: Context) {
        val listComm = getListComm(context)
        val listChoice = ArrayList<Communication>()
        commAnswer = getRandomComm()
        listChoice.add(commAnswer)
        listComm?.removeAt(indexAnswer)
        val choice1 = listComm?.get(Random.nextInt(0, listComm.size))
        listChoice.add(choice1!!)
        listComm.remove(choice1)
        val choice2 = listComm.get(Random.nextInt(0, listComm.size))
        listChoice.add(choice2)
        listComm.remove(choice2)
        val choice3 = listComm.get(Random.nextInt(0, listComm.size))
        listChoice.add(choice3)
        listChoice.shuffle()
        Log.d("TRIEUVD", "get choices: ${listChoice[0].viSentence}, ${listChoice[1].viSentence}, ${listChoice[2].viSentence}, ${listChoice[3].viSentence}")
        listChoiceData.value = listChoice
    }

    fun getSoundAnswer() {
        soundAnswerData.value = commAnswer.nameSound
    }

    fun checkViResult() {
        isAnswer.value = choicePiked.equals(commAnswer.viSentence, ignoreCase = true)
    }

    fun checkEnResult() {
        isAnswer.value = choicePiked.equals(commAnswer.enSentence, ignoreCase = true)
    }
}