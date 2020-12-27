package com.example.learnenglish.ui.vocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnenglish.R;
import com.example.learnenglish.adapter.VocabularyAdapter;
import com.example.learnenglish.database.VocabularyDatabase;
import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;

public class VocabularyFragment extends Fragment {
    ArrayList<Vocabulary> vocabularyArrayList;
    VocabularyAdapter vocabularyAdapter;
    GridView gridView;
    VocabularyDatabase vocabularyDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        gridView = view.findViewById(R.id.gripview);

        vocabularyArrayList = new ArrayList<>();
        vocabularyDatabase = new VocabularyDatabase(getActivity());
        vocabularyArrayList = vocabularyDatabase.getListVocabulary();
        Log.d("TRIEUVD", vocabularyArrayList.size() + "");
        vocabularyAdapter = new VocabularyAdapter(getActivity(), vocabularyArrayList, R.layout.content_layout);
        gridView.setAdapter(vocabularyAdapter);
        gridView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        gridView.setVerticalScrollBarEnabled(false);

        vocabularyAdapter.setListener(new VocabularyAdapter.VocabularyAdapterListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getActivity(), VocaItemActivity.class);
                intent.putExtra("position", vocabularyArrayList.get(pos));
                intent.putExtra("vocabulary_title", vocabularyArrayList.get(pos).getEnTopicVocabulary());
                startActivity(intent);
            }
        });
        return view;
    }
}
