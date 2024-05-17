package com.example.swiftshopapplication;

public class Product{
    private int imageResId; // Resource ID for the image
    private String name;
    private String description;
    private double price;

    // Default constructor is necessary for Firebase data to object mapping
    public Product() {
    }

    // Constructor with parameters
    public Product(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }
    // Getters and Setters
    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
