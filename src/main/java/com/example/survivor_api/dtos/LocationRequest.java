package com.example.survivor_api.dtos;

import jakarta.validation.constraints.NotNull;

public class LocationRequest {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    // Getters and Setters
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

}
