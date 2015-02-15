package com.pc.gridimagesearch.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageResult {
    private String fullUrl;
    private String thumbUrl;
    private String title;



    private int thumbHeight;
    private int thumbWidth;
    private int fullHeight;
    private int fullWidth;


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
