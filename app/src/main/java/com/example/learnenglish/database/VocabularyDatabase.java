package com.example.learnenglish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnenglish.model.VocaTopic;

import java.util.ArrayList;

public class VocabularyDatabase extends DBHelper{
    private Context context;

    public VocabularyDatabase(Context context) {
        super(context);
    }


    public ArrayList<VocaTopic> getListVocabulary() {
        VocaTopic vocabulary;
        ArrayList<VocaTopic> vocabularyArrayList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM VocaTopic");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new VocaTopic(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            vocabularyArrayList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return vocabularyArrayList;
    }
    public VocaTopic getListVocabularyFromId(int id) {
        VocaTopic vocabulary = null;
        //List<Vocabulary> vocabularyList = new ArrayList<>();
        VocabularyDatabase db = new VocabularyDatabase(context);
        db.openDatabase();
        Cursor cursor = db.getDataFromSQLite("SELECT * FROM VocaTopic WHERE Id = '"+id+"'");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new VocaTopic(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            //vocabularyList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        db.closeDatabase();
        return vocabulary;
    }

    public void updatePointById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PointRequired", 0);
        db.update("VocaTopic", values, "Id = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
