package com.example.survivor_api.services;

import com.example.survivor_api.exceptions.NotFoundException;
import com.example.survivor_api.models.Survivor;
import com.example.survivor_api.models.Location;
import com.example.survivor_api.repository.SurvivorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurvivorService {
    private final SurvivorRepository repository;

    public SurvivorService(SurvivorRepository repository) {
        this.repository = repository;
    }

    public Survivor createSurvivor(Survivor survivor) {
        if (survivor.getInventory() == null) {
            survivor.setInventory(Collections.emptyList());
        }
        survivor.setInfected(false);
        return repository.save(survivor);
    }

    public Survivor updateLocation(String id, double lat, double lon) {
        Survivor survivor = repository.findById(id).orElseThrow(() -> new NotFoundException("Survivor not found"));
        survivor.setLastLocation(new Location(lat, lon));
        return repository.save(survivor);
    }

    public Survivor setInfected(String id, boolean infected) {
        Survivor survivor = repository.findById(id).orElseThrow(() -> new NotFoundException("Survivor not found"));
        survivor.setInfected(infected);
        return repository.save(survivor);
    }

    public Optional<Survivor> findById(String id) {
        return repository.findById(id);
    }

    public List<Survivor> findAll() {
        return repository.findAll();
    }

}
