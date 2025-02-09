package com.cleaningService.user_ws.model;

public class Service {
    private int id;
    private String name;
    private String description;
    private double price;
    private int category_id;
    private String image;

    // Default constructor
    public Service() {
    }

    // Constructor with all fields
    public Service(int id, String name, String description, double price, int category_id, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
    }

    // Constructor without ID (for creating a new service)
    public Service(String name, String description, double price, int category_id, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image = image;
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
