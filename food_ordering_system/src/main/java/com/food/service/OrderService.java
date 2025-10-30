package com.food.service;

import com.food.model.Customer;
import com.food.model.MenuItem;
import com.food.model.OrderItem;
import com.food.model.Orders;
import com.food.repository.OrderItemRepository;
import com.food.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Create a new order

    @Transactional
    public Orders createOrder(Orders order) {
        // Save the order
        Orders savedOrder = orderRepository.save(order);

        // Refresh the entity to get database-generated fields like orderTime
       // Ensure all changes are pushed to DB
        return orderRepository.findById(savedOrder.getId()).orElse(savedOrder);
    }


    // Add an item to an existing order
    @Transactional
    public OrderItem addOrderItem(Orders order, MenuItem menuItem, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(quantity);
        return orderItemRepository.save(orderItem);
    }

    // Get all orders for a customer
    public List<Orders> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    // Get all MenuItems of an order
    public List<OrderItem> getOrderedItemsByOrderId(int orderId) {
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        for (OrderItem item : items) {
            MenuItem menu = item.getMenuItem();
            if (menu != null) {
                item.setMenuItemName(menu.getName());
                item.setPrice(menu.getPrice());
                item.setMenuItemId(menu.getId());

                if (menu.getRestaurant() != null) {
                    item.setRestaurantId(menu.getRestaurant().getId());
                    item.setRestaurantName(menu.getRestaurant().getName());
                }
            }
        }

        return items;
    }




    public Orders getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    // Calculate total price of an order
    public double calculateOrderTotal(int orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getMenuItem().getPrice().doubleValue() * item.getQuantity();
        }
        return total;
    }


}
