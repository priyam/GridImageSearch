package com.pc.gridimagesearch.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageResult {
    private String fullUrl;
    private String thumbUrl;
    private  String title;

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

    public ImageResult(JSONObject json){

        try{
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");

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
