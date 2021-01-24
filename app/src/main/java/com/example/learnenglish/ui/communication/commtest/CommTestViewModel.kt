package com.example.learnenglish.ui.communication.commtest

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
    var totalItem = 0
    var isSpeaker = false

    val listSize = MutableLiveData<Int>()
    val commAnswerData = MutableLiveData<Communication>()
    val listChoiceData = MutableLiveData<ArrayList<Communication>>()
    val isAnswer = MutableLiveData<String>()
    val isBtnTextEnable = MutableLiveData<Boolean>()
    val soundAnswerData = MutableLiveData<String>()
    val currentCountData = MutableLiveData<Int>()
    var totalItemData = MutableLiveData<Int>()

    fun getListComm(context: Context): ArrayList<Communication>? {
        commDatabase = CommunicationDatabase(context)
        return commDatabase.getListComm()
    }

    fun getListCommSelected(context: Context) {
        commDatabase = CommunicationDatabase(context)
        listCommSelected = commDatabase.getListCommByTopicId(1)
        totalItem = listCommSelected?.size!!
        totalItemData.value = totalItem
    }

    fun getRandomComm(): Communication {
        var commRandom = Communication()
        listCommSelected?.let {
            if (it.size > 0) {
                commRandom = it[Random.nextInt(0, it.size)]
                listCommSelected?.remove(commRandom)
                commAnswerData.value = commRandom
            }
        }
        Log.d("TRIEUVD", "listCommSelected: ${listCommSelected?.size}")
        currentCountData.value = totalItem - listCommSelected?.size!!
        return commRandom
    }

    fun setChoicePicked(choicePiked: String) {
        this.choicePiked = choicePiked
        setEnableBtnText(true)
    }

    fun getRandomMutileChoice(context: Context) {
        if (listCommSelected?.size!! > 0) {
            val listComm = getListComm(context)
            val listChoice = ArrayList<Communication>()
            commAnswer = getRandomComm()
            listChoice.add(commAnswer)
            for (i in 0 until listComm?.size!!) {
                if (listComm[i].id == commAnswer.id) {
                    listComm.removeAt(i)
                    break
                }
            }
            /*listComm?.forEach {
                if (it.id == commAnswer.id) listComm.remove(it)
            }*/
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
    }

    fun getSoundAnswer() {
        soundAnswerData.value = commAnswer.nameSound
    }

    fun setEnableBtnText(isEnable: Boolean) {
        isBtnTextEnable.value = isEnable
    }

    fun checkViResult() {
        if (choicePiked.equals(commAnswer.viSentence, ignoreCase = true)) {
            isAnswer.value = "1"
        } else {
            isAnswer.value = commAnswer.viSentence
        }

    }

    fun checkEnResult() {
        if (choicePiked.equals(commAnswer.enSentence, ignoreCase = true)) {
            isAnswer.value = "1"
        } else {
            isAnswer.value = commAnswer.enSentence
        }
    }
}