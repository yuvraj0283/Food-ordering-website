package com.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    @JsonIgnore
    private MenuItem menuItem;

    @Column(nullable = false)
    private int quantity;

    // Helper transient fields for cart display purposes (not persisted)
    @Transient
    private String menuItemName;

    @Transient
    private BigDecimal price;

    @Transient
    private int restaurantId;

    @Transient
    private String restaurantName;
    // Getters and Setters



    public void setOrder(Orders order) { this.order = order; }

    public MenuItem getMenuItem() { return menuItem; }

    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getMenuItemName() { return menuItemName; }

    public void setMenuItemName(String menuItemName) { this.menuItemName = menuItemName; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public void setMenuItemId(int id) {
        if (this.menuItem == null) {
            this.menuItem = new MenuItem();
        }
        this.menuItem.setId(id);
    }

    public int getMenuItemId() {
        if (this.menuItem != null) {
            return this.menuItem.getId();
        }
        return 0;  // Or throw an exception depending on your design
    }



    // getters and setters
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

}
