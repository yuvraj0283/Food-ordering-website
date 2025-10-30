package com.food.repository;

import com.food.model.Customer;
import com.food.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    // Find all orders of a customer
    List<Orders> findByCustomer(Customer customer);
}
