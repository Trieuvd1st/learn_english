package com.example.learnenglish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;

public class VocabularyDatabase extends DBHelper{
    private Context context;

    public VocabularyDatabase(Context context) {
        super(context);
    }


    public ArrayList<Vocabulary> getListVocabulary() {
        Vocabulary vocabulary;
        ArrayList<Vocabulary> vocabularyArrayList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM Chude");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            vocabularyArrayList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return vocabularyArrayList;
    }
    public Vocabulary getListVocabularyFromId(int id) {
        Vocabulary vocabulary = null;
        //List<Vocabulary> vocabularyList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM Chude WHERE Id = '"+id+"'");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new Vocabulary(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            //vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return vocabulary;
    }

    public void updatePointById(Vocabulary vocabulary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PointRequired", 0);
        db.update("Chude", values, "Id = ?", new String[] { String.valueOf(vocabulary.getIdVocabulary()) });
        db.close();
    }
}
