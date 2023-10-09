package com.exemple.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.exemple.model.ThemeParkRide;

import java.util.List;

@Repository
public interface ThemeParkRideRepository extends MongoRepository<ThemeParkRide, String> {
	List<ThemeParkRide> findByName(String name);
}