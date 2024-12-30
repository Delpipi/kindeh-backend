package net.alexandrepaulkouame.services;

import net.alexandrepaulkouame.dto.CustomerDTO;
import net.alexandrepaulkouame.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustmerById(UUID id);
    Customer createCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(UUID uuid,  CustomerDTO customerDTO);
    void deleteCustomerById(UUID id);
}
