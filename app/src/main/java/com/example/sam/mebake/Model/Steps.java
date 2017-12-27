package com.example.sam.mebake.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sam.mebake.Model.Recipes;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 11/29/17.
 */

public class Steps implements Parcelable{
    @SerializedName("id")
    private String id;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("videoURL")
    private String videoURL;
    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getShortDescription ()
    {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getVideoURL ()
    {
        return videoURL;
    }

    public void setVideoURL (String videoURL)
    {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL ()
    {
        return thumbnailURL;
    }

    public void setThumbnailURL (String thumbnailURL)
    {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(id);
            parcel.writeString(shortDescription);
            parcel.writeString(description);
            parcel.writeString(videoURL);
            parcel.writeString(thumbnailURL);
    }

    private Steps(Parcel in){
        id = in.readString();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();


    }
}
