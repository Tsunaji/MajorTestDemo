package com.digitopolis.majortestdemo;

/**
 * Created by User on 10/14/2015.
 */
public class Model implements ItemSection{
    private String image;
    private String image2;
    private String tag;

    public Model(String image, String image2, String tag) {
        this.image = image;
        this.image2 = image2;
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public String getImage2() {
        return image2;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
