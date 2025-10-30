package com.food.repository;

import com.food.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    // Custom query method (Spring will generate automatically)
    List<MenuItem> findByRestaurantId(int restaurantId);

}
