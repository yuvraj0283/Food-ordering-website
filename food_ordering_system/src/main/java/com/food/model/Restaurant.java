package com.food.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String address;


    // Getters and Setters
    public int getId() { return id; }


    public String getName() { return name; }


    public String getAddress() { return address; }




}
