package com.example.survivor_api.dtos;

import jakarta.validation.constraints.NotNull;

public class InfectedRequest {
    @NotNull
    private Boolean infected;

    // Getters and Setters
    public Boolean getInfected() { return infected; }
    public void setInfected(Boolean infected) { this.infected = infected; }
}
