package com.pc.gridimagesearch.models;

public enum ImageSize {
    ICON("Small"),
    SMALL ("Medium"),
    MEDIUM("Medium"),
    LARGE("Medium"),
    EXTRA_LARGE("Medium"),
    EXTRA_EXTRA_LARGE("Large"),
    HUGE("Extra Large"),
    NONE("None");

    ImageSize(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}

/*
imgsz=icon restricts results to small images
imgsz=small|medium|large|xlarge restricts results to medium-sized images
imgsz=xxlarge restricts results to large images
imgsz=huge restricts resykts to extra-large images
 */