package com.food.service;

import com.food.model.MenuItem;
import com.food.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getMenuItemsByRestaurantId(int restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem getMenuItemById(int menuItemId) {
        return menuItemRepository.findById(menuItemId).orElse(null);
    }

}
