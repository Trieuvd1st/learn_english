package com.example.learnenglish.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.adapter.VocabularyItemAdapter;
import com.example.learnenglish.database.VocabularyDatabase;
import com.example.learnenglish.database.VocabularyItemDatabase;
import com.example.learnenglish.listener.OnQuestionCallbackVocabularyItem;
import com.example.learnenglish.model.Vocabulary;
import com.example.learnenglish.model.VocabularyItem;
import com.example.learnenglish.R;

import java.util.List;

public class VocabularyItemActivity extends AppCompatActivity implements OnQuestionCallbackVocabularyItem {

    private Vocabulary vocabulary;
    int level;
    ListView lv_item;
    VocabularyItemAdapter adapterItem;
    Button btnNext;
    VocabularyItemDatabase vocabularyItemDatabase;
    VocabularyDatabase vocabularyDatabase;
    List<VocabularyItem> itemArrayList;
    private MediaPlayer mediaPlayer;
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_item);
        getIncomingData();
        getSupportActionBar().setTitle(vocabulary.getNameVocabulary());
        lv_item = findViewById(R.id.listview_item);
        vocabularyItemDatabase = new VocabularyItemDatabase(this);
        vocabularyDatabase = new VocabularyDatabase(this);
        //Toast.makeText(this, vocabulary.getIdVocabulary()+" "+level, Toast.LENGTH_SHORT).show();
        itemArrayList = vocabularyItemDatabase.getListLevelVocabularyItem(vocabulary.getIdVocabulary());
        adapterItem = new VocabularyItemAdapter(this, R.layout.stream_item_vocabulary, itemArrayList);
        adapterItem.setListener(this);
        lv_item.setAdapter(adapterItem);
    }

    private void getIncomingData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("position")) {
                vocabulary = (Vocabulary) intent.getSerializableExtra("position");
            }
        }
    }

    @Override
    public void onClickQuestion(VocabularyItem item) {
        mediaPlayer = MediaPlayer.create(this, getResourcesIdFromName(item.getSoundItem()));
        mediaPlayer.start();
    }

    @RawRes
    private int getResourcesIdFromName(String name) {
        return getResources().getIdentifier(name, "raw", getPackageName());
    }
}
