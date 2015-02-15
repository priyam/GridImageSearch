package com.pc.gridimagesearch.models;

public class ImageRequestFilters {

    private static ImageSize size = ImageSize.NONE;
    private static String color;
    private static ImageType type = ImageType.NONE;
    private static String site;

    private ImageRequestFilters()
    {
    }

    public static ImageSize getSize() {
        return size;
    }

    public static void setSize(ImageSize size) {
        ImageRequestFilters.size = size;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        ImageRequestFilters.color = color;
    }

    public static ImageType getType() {
        return type;
    }

    public static void setType(ImageType type) {
        ImageRequestFilters.type = type;
    }

    public static String getSite() {
        return site;
    }

    public static void setSite(String site) {
        ImageRequestFilters.site = site;
    }

    public static void clearFilters(){
        ImageRequestFilters.size = ImageSize.NONE;
        ImageRequestFilters.color = null;
        ImageRequestFilters.site = null;
        ImageRequestFilters.type = ImageType.NONE;

    }
}

