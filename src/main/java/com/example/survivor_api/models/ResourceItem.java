package com.example.survivor_api.models;

public class ResourceItem {
    private String resourceName;
    private int quantity;

    public ResourceItem(String resourceName, int quantity) {
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
