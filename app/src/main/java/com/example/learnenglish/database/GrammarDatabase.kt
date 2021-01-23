package com.example.learnenglish.database

import android.content.ContentValues
import android.content.Context
import com.example.learnenglish.model.Exercise
import com.example.learnenglish.model.Grammar

class GrammarDatabase(context: Context?) : DBHelper(context) {
    private val context: Context? = null

    fun getListGrammar(): ArrayList<Grammar> {
        var grammar: Grammar?
        val grammarArrayList: MutableList<Grammar> = ArrayList()
        val db = GrammarDatabase(context)
        db.openDatabase()
        val cursor = db.getDataFromSQLite("SELECT * FROM Grammar")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            grammar = Grammar(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(
                    3
                )
            )
            grammarArrayList.add(grammar)
            cursor.moveToNext()
        }
        cursor.close()
        db.closeDatabase()
        return grammarArrayList as ArrayList<Grammar>
    }

    fun getListGrammarExercise(): ArrayList<Exercise> {
        var exercise: Exercise?
        val exerciseArrayList: MutableList<Exercise> = ArrayList()
        val db = GrammarDatabase(context)
        db.openDatabase()
        val cursor = db.getDataFromSQLite("SELECT * FROM GrammarExercise")
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            exercise = Exercise(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6)
            )
            exerciseArrayList.add(exercise)
            cursor.moveToNext()
        }
        cursor.close()
        db.closeDatabase()
        return exerciseArrayList as ArrayList<Exercise>
    }

    fun updatePointById(id: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("PointRequired", 0)
        db.update("Grammar", values, "Id = ?", arrayOf(id.toString()))
        db.close()
    }
}