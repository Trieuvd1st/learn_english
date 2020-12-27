package com.example.learnenglish.model;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private int idVocabulary;
    private String enTopicVocabulary;
    private String viTopicVoCabulary;
    private String imageVocabulary;
    private int pointRequired;

    public Vocabulary(int idVocabulary, String enTopicVocabulary, String viTopicVoCabulary, String imageVocabulary, int pointRequired) {
        this.idVocabulary = idVocabulary;
        this.enTopicVocabulary = enTopicVocabulary;
        this.viTopicVoCabulary = viTopicVoCabulary;
        this.imageVocabulary = imageVocabulary;
        this.pointRequired = pointRequired;
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public String getEnTopicVocabulary() {
        return enTopicVocabulary;
    }

    public void setEnTopicVocabulary(String enTopicVocabulary) {
        this.enTopicVocabulary = enTopicVocabulary;
    }

    public String getViTopicVoCabulary() {
        return viTopicVoCabulary;
    }

    public void setViTopicVoCabulary(String viTopicVoCabulary) {
        this.viTopicVoCabulary = viTopicVoCabulary;
    }

    public String getImageVocabulary() {
        return imageVocabulary;
    }

    public void setImageVocabulary(String imageVocabulary) {
        this.imageVocabulary = imageVocabulary;
    }

    public int getPointRequired() {
        return pointRequired;
    }

    public void setPointRequired(int pointRequired) {
        this.pointRequired = pointRequired;
    }
}
