package com.example.learnenglish.database

import android.content.Context
import com.example.learnenglish.model.Grammar

class GrammarDatabase(context: Context?) : DBHelper(context) {
    private val context: Context? = null

    fun getListGrammar(): ArrayList<Grammar>? {
        var grammar: Grammar?
        val grammarArrayList: MutableList<Grammar> = ArrayList()
        val db = GrammarDatabase(context)
        db.openDatabase()
        val cursor = db.getDataFromSQLite("SELECT * FROM Grammar")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            grammar = Grammar(cursor.getInt(0), cursor.getString(1), cursor.getString(2))
            grammarArrayList.add(grammar)
            cursor.moveToNext()
        }
        cursor.close()
        db.closeDatabase()
        return grammarArrayList as ArrayList<Grammar>
    }
}