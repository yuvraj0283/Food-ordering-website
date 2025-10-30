package com.food.controller;

import com.food.model.Customer;
import com.food.security.JwtUtil;
import com.food.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    public CustomerController(CustomerService customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Customer customer) {
        Customer success = customerService.signup(customer);
        if (success != null) {
            return ResponseEntity.ok("Signup successful!");
        }
        return ResponseEntity.badRequest().body("Signup failed.");
    }

    // Login -> returns JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Customer customer = customerService.login(username, password);

        if (customer != null) {
            String token = jwtUtil.generateToken(username);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("customerId", customer.getId());
            response.put("username", customer.getName());

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
