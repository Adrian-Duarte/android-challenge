package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

public class DataTag {

    // Attributes
    @SerializedName("data")
    private ListTag data;

    // Getters && Setters
    public ListTag getData() {
        return data;
    }
    public void setData(ListTag data) {
        this.data = data;
    }

}