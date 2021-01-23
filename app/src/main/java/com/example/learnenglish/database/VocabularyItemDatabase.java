package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;

import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabularyItemDatabase extends DBHelper {
    private Context context;

    public VocabularyItemDatabase(Context context) {
        super(context);
    }

    //    public List<VocabularyItem> getListVocabularyItem(int idchude) {
//        VocabularyItem vocabularyItem = null;
//        List<VocabularyItem> itemList = new ArrayList<>();
//        VocabularyItemDatabase database = new VocabularyItemDatabase(context);
//        database.openDatabase();
//        Cursor cursor = database.getDataFromSQLite("SELECT * FROM Itemchude WHERE Idchude = '"+idchude+"'");
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            vocabularyItem = new VocabularyItem(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
//            itemList.add(vocabularyItem);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        database.closeDatabase();
//        return itemList;
//    }
    public List<Vocabulary> getListVocabularyItem(int topicId) {
        Vocabulary vocabulary = null;
        List<Vocabulary> itemList = new ArrayList<>();
        VocabularyItemDatabase database = new VocabularyItemDatabase(context);
        database.openDatabase();
        Cursor cursor = database.getDataFromSQLite("SELECT * FROM Vocabulary WHERE TopicId = '" + topicId + "'");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            vocabulary = new Vocabulary(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            itemList.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        database.closeDatabase();
        return itemList;
    }
}
