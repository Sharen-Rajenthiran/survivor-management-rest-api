package com.example.survivor_api.controllers;

import com.example.survivor_api.dtos.CreateSurvivorRequest;
import com.example.survivor_api.dtos.InfectedRequest;
import com.example.survivor_api.dtos.LocationRequest;
import com.example.survivor_api.exceptions.NotFoundException;
import com.example.survivor_api.models.Survivor;
import com.example.survivor_api.services.SurvivorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/survivors")
public class SurvivorController {
    private final SurvivorService service;

    public SurvivorController(SurvivorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Survivor> createSurvivor(@Valid @RequestBody CreateSurvivorRequest request) {
        Survivor survivor = new Survivor(
                request.getName(),
                request.getAge(),
                request.getGender(),
                request.getLastLocation(),
                request.getInventory()
        );
        Survivor saved = service.createSurvivor(survivor);
        return ResponseEntity.created(URI.create("/survivors" + saved.getId())).body(saved);
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<Survivor> updateLocation(
            @PathVariable String id,
            @Valid @RequestBody LocationRequest req
            ) {
        Survivor updated = service.updateLocation(id, req.getLatitude(), req.getLongitude());
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/infected")
    public ResponseEntity<Survivor> setInfected(
            @PathVariable String id,
            @Valid @RequestBody InfectedRequest req
            ) {
        Survivor updated = service.setInfected(id, req.getInfected());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survivor> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Survivor not found"));
    }

    @GetMapping
    public ResponseEntity<List<Survivor>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }


}
