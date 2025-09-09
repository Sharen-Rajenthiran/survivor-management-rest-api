package com.example.survivor_api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "survivors")
public class Survivor {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private Location lastLocation;
    private boolean infected = false;
    private List<ResourceItem> inventory;

    public Survivor(String name, int age, String gender, Location lastLocation, List<ResourceItem> inventory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lastLocation = lastLocation;
        this.inventory = inventory;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Location getLastLocation() { return lastLocation; }
    public void setLastLocation(Location lastLocation) { this.lastLocation = lastLocation; }

    public boolean isInfected() { return infected; }
    public void setInfected(boolean infected) { this.infected = infected; }

    public List<ResourceItem> getInventory() { return inventory; }
    public void setInventory(List<ResourceItem> inventory) { this.inventory = inventory; }

}
