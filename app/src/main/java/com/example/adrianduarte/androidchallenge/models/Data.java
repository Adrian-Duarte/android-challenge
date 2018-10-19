package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    // Attributes
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("items")
    private List<Item> items;
    @SerializedName("name")
    private String name;

    // Getters && Setters
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}