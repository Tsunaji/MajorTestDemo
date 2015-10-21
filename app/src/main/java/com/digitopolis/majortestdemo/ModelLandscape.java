package com.digitopolis.majortestdemo;

/**
 * Created by User on 10/14/2015.
 */
public class ModelLandscape implements ItemSection {
    String image;
    String tag;

    public ModelLandscape(String image, String tag) {
        this.image = image;
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
