package com.example.learnenglish.ui.vocabulary.topic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnenglish.R;
import com.example.learnenglish.model.Vocabulary;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VocabularyAdapter extends BaseAdapter {
    private final Context context;
    private final List<Vocabulary> vocabularyList;
    private final int layout;
    private VocabularyAdapterListener listener;

    public void setListener(VocabularyAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
            viewHolder.tvEnTopic = view.findViewById(R.id.tvEnTopic);
            viewHolder.tvViTopic = view.findViewById(R.id.tvViTopic);
            viewHolder.btnDetail = view.findViewById(R.id.btnDetail);
            viewHolder.imageContent = view.findViewById(R.id.image);
            viewHolder.ivPoint = view.findViewById(R.id.ivPointRequired);
            view.setTag(viewHolder);
        } else viewHolder = (ViewHolder) view.getTag();

        Vocabulary vocabulary = vocabularyList.get(i);
        InputStream ims = null;
        try {
            Log.d("duc123", "getView: " + vocabulary.getImageVocabulary());
            ims = context.getAssets().open("img/" + vocabulary.getImageVocabulary() + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(ims, null);
        viewHolder.imageContent.setImageDrawable(d);
        viewHolder.tvEnTopic.setText(vocabulary.getEnTopicVocabulary());
        viewHolder.tvViTopic.setText(vocabulary.getViTopicVoCabulary());
        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(i);
            }
        });
        if (vocabulary.getPointRequired() > 0) {
            viewHolder.ivPoint.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivPoint.setVisibility(View.GONE);
        }
        return view;
    }

    public VocabularyAdapter(Context context, List<Vocabulary> vocabularyList, int layout) {
        this.context = context;
        this.vocabularyList = vocabularyList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return vocabularyList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public interface VocabularyAdapterListener {
        void onItemClick(int pos);
    }

    private class ViewHolder {
        ImageView imageContent;
        TextView tvEnTopic;
        TextView tvViTopic;
        Button btnDetail;
        ImageView ivPoint;
    }

}
