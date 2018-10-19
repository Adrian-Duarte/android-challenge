package com.example.adrianduarte.androidchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Item implements Parcelable{

    // Attributes
    @SerializedName("id")
    private String id;
    @SerializedName("datetime")
    private Integer datetime;
    @SerializedName("description")
    private String description;
    @SerializedName("downs")
    private Integer downs;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("ups")
    private Integer ups;
    @SerializedName("views")
    private Integer views;

    protected Item(Parcel in) {
        id = in.readString();
        datetime = in.readInt();
        description = in.readString();
        images = new ArrayList<>();
        in.readTypedList(images, Image.CREATOR);
        downs = in.readInt();
        link = in.readString();
        title = in.readString();
        ups = in.readInt();
        views = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(datetime);
        dest.writeString(description);
        dest.writeTypedList(images);
        dest.writeInt(downs);
        dest.writeString(link);
        dest.writeString(title);
        dest.writeInt(ups);
        dest.writeInt(views);
    }

    // Getters && Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getDatetime() {
        return datetime;
    }
    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public Integer getDowns() {
        return downs;
    }
    public void setDowns(Integer downs) {
        this.downs = downs;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getUps() {
        return ups;
    }
    public void setUps(Integer ups) {
        this.ups = ups;
    }
    public Integer getViews() {
        return views;
    }
    public void setViews(Integer views) {
        this.views = views;
    }

}