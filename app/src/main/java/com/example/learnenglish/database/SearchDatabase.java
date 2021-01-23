package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class SearchDatabase extends DBHelper {
    private Context context;

    public SearchDatabase(Context context) {
        super(context);
    }

    public List<Vocabulary> getVocabularyItemByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "EnWord", "ViWord", "ImageId", "SoundId", "Spell", "Example"};
        String tableName = "Vocabulary";
        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "EnWord LIKE ?", new String[]{"%" + name + "%"},
                null, null, null);
        List<Vocabulary> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                vocabulary.setEnWord(cursor.getString(cursor.getColumnIndex("EnWord")));
                vocabulary.setViWord(cursor.getString(cursor.getColumnIndex("ViWord")));
                vocabulary.setImageId(cursor.getString(cursor.getColumnIndex("ImageId")));
                vocabulary.setSoundId(cursor.getString(cursor.getColumnIndex("SoundId")));
                vocabulary.setSpell(cursor.getString(cursor.getColumnIndex("Spell")));
                vocabulary.setExample(cursor.getString(cursor.getColumnIndex("Example")));
                result.add(vocabulary);
            } while (cursor.moveToNext());
        }

        //cursor.close();
        return result;
    }

}

