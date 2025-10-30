package com.food.controller;

import com.food.model.MenuItem;
import com.food.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Get menu items by restaurant ID
    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItem> getMenuByRestaurant(@PathVariable int restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }

    // Get single menu item by ID
    @GetMapping("/{menuItemId}")
    public MenuItem getMenuItemById(@PathVariable int menuItemId) {
        return menuItemService.getMenuItemById(menuItemId);
    }
}
