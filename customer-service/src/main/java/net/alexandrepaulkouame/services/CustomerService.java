package net.alexandrepaulkouame.services;

import net.alexandrepaulkouame.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustmerById(UUID id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(UUID uuid,  Customer customer);
    void deleteCustomerById(UUID id);
}
