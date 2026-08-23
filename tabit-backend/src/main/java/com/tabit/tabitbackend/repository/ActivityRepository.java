package com.tabit.tabitbackend.repository;

import com.tabit.tabitbackend.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    /**
     * Returns all activities for a user, newest first.
     * Callers typically limit the result to the most recent 50 entries.
     */
    List<Activity> findByUserIdOrderByCreatedAtDesc(String userId);
}