package com.food.service;

import com.food.model.Customer;
import com.food.repository.CustomerRepository;
import com.food.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Signup
    public Customer signup(Customer customer) {
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(customer.getPassword(), salt);
        customer.setPassword(hashedPassword);
        customer.setSalt(salt);

        return customerRepository.save(customer);
    }

    // Login
    public Customer login(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            String hashedInput = PasswordUtil.hashPassword(password, customer.getSalt());
            if (hashedInput.equals(customer.getPassword())) {
                return customer;
            }
        }
        return null;
    }
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }
}
