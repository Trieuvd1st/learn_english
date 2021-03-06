package com.example.learnenglish.model;

import java.io.Serializable;

public class VocabularyItem implements Serializable {
    private int idItem;
    private int idVocabulary;
    private String englishWordItem;
    private String vietnameseWordItem;
    private String imageItem;
    private String soundItem;
    private String spell;
    private String example;

    public VocabularyItem(int idItem, int idVocabulary,String englishWordItem, String vietnameseWordItem, String imageItem, String soundItem, String spell, String example) {
        this.idItem = idItem;
        this.idVocabulary = idVocabulary;
        this.englishWordItem = englishWordItem;
        this.vietnameseWordItem = vietnameseWordItem;
        this.imageItem = imageItem;
        this.soundItem = soundItem;
        this.spell = spell;
        this.example = example;
    }

    public VocabularyItem() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public String getEnglishWordItem() {
        return englishWordItem;
    }

    public void setEnglishWordItem(String englishWordItem) {
        this.englishWordItem = englishWordItem;
    }

    public String getVietnameseWordItem() {
        return vietnameseWordItem;
    }

    public void setVietnameseWordItem(String vietnameseWordItem) {
        this.vietnameseWordItem = vietnameseWordItem;
    }

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public String getSoundItem() {
        return soundItem;
    }

    public void setSoundItem(String soundItem) {
        this.soundItem = soundItem;
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
