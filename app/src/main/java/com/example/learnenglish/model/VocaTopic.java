package com.example.learnenglish.model;

import java.io.Serializable;

public class VocaTopic implements Serializable {
    private int id;
    private String enName;
    private String viName;
    private String imageId;
    private int pointRequired;

    public VocaTopic(int id, String enName, String viName, String imageId, int pointRequired) {
        this.id = id;
        this.enName = enName;
        this.viName = viName;
        this.imageId = imageId;
        this.pointRequired = pointRequired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getViName() {
        return viName;
    }

    public void setViName(String viName) {
        this.viName = viName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getPointRequired() {
        return pointRequired;
    }

    public void setPointRequired(int pointRequired) {
        this.pointRequired = pointRequired;
    }
}
