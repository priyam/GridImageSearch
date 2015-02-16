package com.pc.gridimagesearch.models;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageResult implements Parcelable {
    private String fullUrl;
    private String thumbUrl;
    private String title;
    private int thumbHeight;
    private int thumbWidth;
    private int fullHeight;
    private int fullWidth;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(fullUrl);
        dest.writeString(thumbUrl);
        dest.writeString(title);
        dest.writeInt(thumbHeight);
        dest.writeInt(fullHeight);
        dest.writeInt(thumbWidth);
        dest.writeInt(fullWidth);
    }

    public static final Parcelable.Creator<ImageResult> CREATOR
            = new Parcelable.Creator<ImageResult>() {
        @Override
        public ImageResult createFromParcel(Parcel in) {
            return new ImageResult(in);
        }

        @Override
        public ImageResult[] newArray(int size) {
            return new ImageResult[size];
        }
    };

    private ImageResult(Parcel in) {
        fullUrl = in.readString();
        thumbUrl = in.readString();
        title = in.readString();
        thumbHeight = in.readInt();
        fullHeight = in.readInt();
        thumbWidth = in.readInt();
        fullWidth = in.readInt();
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public int getFullHeight() {
        return fullHeight;
    }

    public void setFullHeight(int fullHeight) {
        this.fullHeight = fullHeight;
    }

    public int getFullWidth() {
        return fullWidth;
    }

    public void setFullWidth(int fullWidth) {
        this.fullWidth = fullWidth;
    }

    public ImageResult(JSONObject json){

        try{
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
            this.fullHeight = json.getInt("height");
            this.fullWidth = json.getInt("width");
            this.thumbHeight = json.getInt("tbHeight");
            this.thumbWidth = json.getInt("tbWidth");

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<ImageResult> fromJsonArray(JSONArray array){

        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for (int i = 0; i < array.length();i++){
            try{
                results.add(new ImageResult(array.getJSONObject(i)));

            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }


}
