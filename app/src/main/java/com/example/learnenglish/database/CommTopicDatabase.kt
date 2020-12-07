package com.example.learnenglish.database

import android.content.Context
import com.example.learnenglish.model.CommTopic
import java.util.*

class CommTopicDatabase(context: Context) : DBHelper(context) {
    private val context: Context? = null

    fun getListCommTopic(): ArrayList<CommTopic>? {
        var commTopic: CommTopic? = null
        val commTopicArrayList: MutableList<CommTopic> = ArrayList()
        val db = CommTopicDatabase(context!!)
        db.openDatabase()
        val cursor = db.getDataFromSQLite("SELECT * FROM CommTopic")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            commTopic = CommTopic(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
            commTopicArrayList.add(commTopic)
            cursor.moveToNext()
        }
        cursor.close()
        db.closeDatabase()
        return commTopicArrayList as ArrayList<CommTopic>
    }

}