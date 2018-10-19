package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListTag {

    // Attributes
    @SerializedName("tags")
    private List<Tag> tags;

    // Getters && Setters
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}