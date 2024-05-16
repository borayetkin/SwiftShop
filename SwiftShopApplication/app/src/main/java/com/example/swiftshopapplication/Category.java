package com.example.swiftshopapplication;

public class Category {
    private String name;
    private int imageRes;

    public Category(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }
}


