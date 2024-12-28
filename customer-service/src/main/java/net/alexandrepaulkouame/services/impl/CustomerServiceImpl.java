package net.alexandrepaulkouame.services.impl;

import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.repositories.CustomerRepository;
import net.alexandrepaulkouame.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustmerById(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer customerExt = customerRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        modelMapper.typeMap(Customer.class, Customer.class).addMappings(mapper ->
                mapper.skip(Customer::setPassword)
        );

        modelMapper.map(customer, customerExt);
        return customerRepository.save(customerExt);
    }

    @Override
    public void deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
    }
}
