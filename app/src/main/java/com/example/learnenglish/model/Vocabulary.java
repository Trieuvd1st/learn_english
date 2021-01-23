package com.example.learnenglish.model;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private int id;
    private int topicId;
    private String enWord;
    private String viWord;
    private String imageId;
    private String soundId;
    private String spell;
    private String example;

    public Vocabulary() {
    }

    public Vocabulary(int id, int topicId, String enWord, String viWord, String imageId, String soundId, String spell, String example) {
        this.id = id;
        this.topicId = topicId;
        this.enWord = enWord;
        this.viWord = viWord;
        this.imageId = imageId;
        this.soundId = soundId;
        this.spell = spell;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }

    public String getViWord() {
        return viWord;
    }

    public void setViWord(String viWord) {
        this.viWord = viWord;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getSoundId() {
        return soundId;
    }

    public void setSoundId(String soundId) {
        this.soundId = soundId;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
