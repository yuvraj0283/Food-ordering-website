package com.food.repository;

import com.food.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    // No need for getAllRestaurants(Session) – JpaRepository provides findAll()
}
