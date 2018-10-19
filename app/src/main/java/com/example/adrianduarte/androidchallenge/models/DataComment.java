package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataComment {

    // Attributes
    @SerializedName("data")
    private List<Comment> data;

    // Getters && Setters
    public List<Comment> getData() {
        return data;
    }
    public void setData(List<Comment> data) {
        this.data = data;
    }

}