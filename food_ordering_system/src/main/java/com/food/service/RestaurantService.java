package com.food.service;

import com.food.model.Restaurant;
import com.food.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        // JPA handles transactions automatically, no need for SessionFactory
        return restaurantRepository.findAll();
    }
}
