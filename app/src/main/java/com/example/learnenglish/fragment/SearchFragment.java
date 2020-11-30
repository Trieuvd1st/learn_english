package com.example.learnenglish.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.fragment.app.Fragment;

import com.example.learnenglish.adapter.SearchAdapter;
import com.example.learnenglish.database.SearchDatabase;
import com.example.learnenglish.listener.OnQuestionCallbackVocabularyItem;
import com.example.learnenglish.model.Vocabulary;
import com.example.learnenglish.model.VocabularyItem;
import com.example.learnenglish.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements OnQuestionCallbackVocabularyItem,
        SearchView.OnQueryTextListener {
    private Vocabulary vocabulary;
    ListView listItem;
    SearchAdapter adapterItem;
    SearchDatabase searchDatabase;
    List<VocabularyItem> itemArrayList;
    private MediaPlayer mediaPlayer;
    SearchView searchView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listItem = view.findViewById(R.id.list_item);
        searchView = view.findViewById(R.id.search);
        searchDatabase = new SearchDatabase(getContext());
        searchView.setOnQueryTextListener(this);
        itemArrayList = new ArrayList<>();
        adapterItem = new SearchAdapter(getContext(), R.layout.stream_item_vocabulary, itemArrayList);
        adapterItem.setListener(this);
        listItem.setAdapter(adapterItem);
        return view;
    }

    @Override
    public void onClickQuestion(VocabularyItem item) {
        mediaPlayer = MediaPlayer.create(getContext(), getResourcesIdFromName(item.getSoundItem()));
        mediaPlayer.start();
    }

    @RawRes
    private int getResourcesIdFromName(String nameSound) {
        return getResources().getIdentifier(nameSound, "raw", getActivity().getPackageName());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.equals("")) {
            itemArrayList = new ArrayList<>();
        } else {
            itemArrayList = searchDatabase.getVocabularyItemByName(searchView.getQuery().toString());
        }
        adapterItem.setData(itemArrayList);
        return false;
    }

}
