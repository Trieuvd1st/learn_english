package com.example.learnenglish.ui.vocabulary.test;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learnenglish.R;
import com.example.learnenglish.utils.custom.ScreenSlidePagerActivity;

public class TestFragment extends Fragment {
    private ListView lv_test;
    private final String[] list_test = {"Unit 1", "Unit 2", "Unit 3", "Unit 4"};

    public TestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test, container, false);
        lv_test = view.findViewById(R.id.lisstview_test);
        ArrayAdapter adapter=new ArrayAdapter(getActivity(), R.layout.custom_dongbaitest, list_test);
        lv_test.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), list_test[position], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ScreenSlidePagerActivity.class);
                intent.putExtra("viTri", position);
                startActivity(intent);
            }
        });
    }
}
