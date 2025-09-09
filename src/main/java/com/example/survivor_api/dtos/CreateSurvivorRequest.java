package com.example.survivor_api.dtos;

import com.example.survivor_api.models.ResourceItem;
import com.example.survivor_api.models.Location;
import jakarta.validation.constraints.*;
import java.util.List;

public class CreateSurvivorRequest {
    @NotBlank
    private String name;
    @Min(0)
    private int age;
    @NotBlank
    private String gender;
    @NotNull
    private Location lastLocation;
    @NotNull
    @Size(min = 0)
    private List<ResourceItem> inventory;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Location getLastLocation() { return lastLocation; }
    public void setLastLocation(Location lastLocation) { this.lastLocation = lastLocation; }

    public List<ResourceItem> getInventory() { return inventory; }
    public void setInventory(List<ResourceItem> inventory) { this.inventory = inventory; }
}
