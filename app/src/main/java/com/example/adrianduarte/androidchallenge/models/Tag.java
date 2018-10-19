package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

public class Tag {

    // Attributes
    @SerializedName("id")
    private String id;
    @SerializedName("background_hash")
    private String backgroundHash;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("followers")
    private Integer followers;
    @SerializedName("name")
    private String name;
    @SerializedName("total_items")
    private Integer totalItems;

    // Getters && Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBackgroundHash() {
        return backgroundHash;
    }
    public void setBackgroundHash(String backgroundHash) {
        this.backgroundHash = backgroundHash;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Integer getFollowers() {
        return followers;
    }
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}