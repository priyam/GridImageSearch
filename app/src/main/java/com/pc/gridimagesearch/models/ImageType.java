package com.pc.gridimagesearch.models;

public enum ImageType {
    FACE ("Face"),
    PHOTO("Photo"),
    CLIPART("Clipart"),
    LINEART("Lineart"),
    NONE("None");

    ImageType (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
