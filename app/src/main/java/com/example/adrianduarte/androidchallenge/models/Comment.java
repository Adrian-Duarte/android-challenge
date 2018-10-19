package com.example.adrianduarte.androidchallenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {

    // Attributes
    @SerializedName("id")
    private Integer id;
    @SerializedName("author")
    private String author;
    @SerializedName("comment")
    private String comment;
    @SerializedName("children")
    private List<Comment> children;

    // Getters && Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<Comment> getChildren() {
        return children;
    }
    public void setChildren(List<Comment> children) {
        this.children = children;
    }

}
