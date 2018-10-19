package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

public class DataImage {

    // Attributes
    @SerializedName("data")
    private Data data;

    // Getters && Setters
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

}