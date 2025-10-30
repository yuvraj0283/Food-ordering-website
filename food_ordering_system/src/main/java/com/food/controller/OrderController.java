package com.food.controller;

import com.food.model.MenuItem;
import com.food.model.OrderItem;
import com.food.model.Orders;
import com.food.model.Customer;
import com.food.service.OrderService;
import com.food.service.CustomerService;
import com.food.service.MenuItemService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final MenuItemService menuItemService;


    public OrderController(OrderService orderService, CustomerService customerService, MenuItemService menuItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.menuItemService = menuItemService;
    }

    // Place a new order
    @PostMapping
    public Orders createOrder(@RequestParam int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        Orders order = new Orders();
        order.setCustomer(customer);
        orderService.createOrder(order);
        return order;
    }

    // Add items to an order
    @PostMapping("/{orderId}/items")
    public String addOrderItem(@PathVariable int orderId,
                               @RequestParam int menuItemId,
                               @RequestParam int quantity) {
        Orders order = orderService.getOrderById(orderId);
        MenuItem item = menuItemService.getMenuItemById(menuItemId);
        if (order != null && item != null) {
            orderService.addOrderItem(order, item, quantity);
            return "Item added to order";
        }
        return "Order or MenuItem not found";
    }

    // Get orders of a customer
    @GetMapping("/customer/{customerId}")
    public List<Orders> getOrdersByCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return orderService.getOrdersByCustomer(customer);
    }

    // Get items in an order
    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderedItems(@PathVariable int orderId) {
        return orderService.getOrderedItemsByOrderId(orderId);
    }


    @GetMapping("/{orderId}/total")
    public String getOrderTotal(@PathVariable int orderId) {
        Orders order = orderService.getOrderById(orderId);
        if (order != null) {
            double total = orderService.calculateOrderTotal(orderId);
            return "Total price: ₹" + total;
        }
        return "Order not found";
    }

}
