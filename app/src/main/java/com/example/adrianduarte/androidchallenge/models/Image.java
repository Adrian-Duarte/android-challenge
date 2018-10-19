package com.example.adrianduarte.androidchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    // Attributes
    @SerializedName("id")
    private String id;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;

    // Constructors
    protected Image(Parcel in) {
        id = in.readString();
        datetime = in.readString();
        description = in.readString();
        link = in.readString();
        title = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(datetime);
        dest.writeString(description);
        dest.writeString(link);
        dest.writeString(title);
    }

    // Getters && Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
}