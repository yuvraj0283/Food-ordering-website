package com.food.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp orderTime;


    // Getters and Setters
    public int getId() { return id; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public Timestamp getOrderTime() { return orderTime; }

}
