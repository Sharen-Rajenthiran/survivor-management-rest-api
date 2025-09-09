package com.example.survivor_api.services;

import com.example.survivor_api.repository.SurvivorRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ReportService {
    private final SurvivorRepository repository;
    private final MongoTemplate mongoTemplate;

    public ReportService(SurvivorRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public Map<String, Object> getReports() {
        long total = repository.count();
        long infected = repository.countByInfected(true);
        long nonInfected = repository.countByInfected(false);

        double infectedPercentage = total == 0 ? 0.0 : (infected * 100.0) / total;
        double nonInfectedPercentage = total == 0 ? 0.0 : (nonInfected * 100.0) / total;

        Map<String, Double> averageResources = computeAverageResourcesPerNonInfected(nonInfected);

        Map<String, Object> result = new HashMap<>();
        result.put("total_survivors", total);
        result.put("infected_count", infected);
        result.put("non_infected_count", nonInfected);
        result.put("infected_percentage", infectedPercentage);
        result.put("non_infected_percentage", nonInfectedPercentage);
        result.put("average_resources_per_non_infected_survivor", averageResources);

        return result;
    }

    private Map<String, Double> computeAverageResourcesPerNonInfected(long nonInfectedCount) {
        Map<String, Double> averages = new HashMap<>();
        if (nonInfectedCount == 0) { return averages; }

        MatchOperation matchNonInfected = match(Criteria.where("infected").is(false));
        UnwindOperation unwind = unwind("inventory");
        GroupOperation groupByResource = group("inventory.resourceName")
                .sum("inventory.quantity")
                .as("totalQuantity");
        Aggregation aggregation = newAggregation(matchNonInfected, unwind, groupByResource);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "survivors", org.bson.Document.class);
        for (Document doc : results) {
            String resourceName = doc.getString("_id");
            Number totalQuantity = (Number) doc.get("totalQuantity");
            double avg = totalQuantity.doubleValue() / (double) nonInfectedCount;
            averages.put(resourceName, avg);
        }

        return averages;
    }


}
