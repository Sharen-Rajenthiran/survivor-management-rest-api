package com.example.survivor_api.repository;

import com.example.survivor_api.models.Survivor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurvivorRepository extends MongoRepository<Survivor, String> {
    long countByInfected(boolean infected);
}
