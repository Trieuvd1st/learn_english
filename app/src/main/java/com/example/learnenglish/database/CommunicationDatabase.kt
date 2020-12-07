package com.example.learnenglish.database

import android.content.Context
import com.example.learnenglish.model.Communication
import java.util.*

class CommunicationDatabase(context: Context) : DBHelper(context) {
    private val context: Context? = null

    fun getListComm(): ArrayList<Communication>? {
        var comm: Communication? = null
        val commArrayList: MutableList<Communication> = ArrayList()
        val db = CommunicationDatabase(context!!)
        db.openDatabase()
        val cursor = db.getDataFromSQLite("SELECT * FROM Communication")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            comm = Communication().apply {
                id = cursor.getInt(0)
                topicId = cursor.getInt(1)
                enSentence = cursor.getString(2)
                viSentence = cursor.getString(3)
                nameSound = cursor.getString(4)
                isSelected = convertSelection(cursor.getInt(5))
            }
            commArrayList.add(comm)
            cursor.moveToNext()
        }
        cursor.close()
        db.closeDatabase()
        return commArrayList as ArrayList<Communication>
    }

}