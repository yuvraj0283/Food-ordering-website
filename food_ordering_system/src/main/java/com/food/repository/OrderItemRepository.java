package com.food.repository;

import com.food.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    // Find order items by order id
    List<OrderItem> findByOrderId(int orderId);
}
