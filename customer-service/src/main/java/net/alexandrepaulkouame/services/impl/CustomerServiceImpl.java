package net.alexandrepaulkouame.services.impl;

import net.alexandrepaulkouame.dto.CustomerDTO;
import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.repositories.CustomerRepository;
import net.alexandrepaulkouame.repositories.RoleRepository;
import net.alexandrepaulkouame.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustmerById(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {

        Set<Role> roles = roleRepository.findByNameIn(customerDTO.roles());

        if (roles.isEmpty()){
            throw new IllegalArgumentException("Aucun role correspond");
        }

        Customer customer = Customer.builder()
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .phoneNumber(customerDTO.phoneNumber())
                .password(customerDTO.password())
                .email(customerDTO.email())
                .roles(roles)
                .documents(customerDTO.documents())
                .build();

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer customerExt = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        Set<Role> roles = roleRepository.findByNameIn(customerDTO.roles());

        if (roles.isEmpty()){
            throw new IllegalArgumentException("Aucun role correspond");
        }

        customerExt.setFirstName(customerDTO.firstName());
        customerExt.setLastName(customerDTO.lastName());
        customerExt.setPhoneNumber(customerDTO.phoneNumber());
        customerExt.setEmail(customerDTO.email());
        customerExt.setActive(false);
        customerExt.setRoles(roles);
        customerExt.setDocuments(customerDTO.documents());

        return customerRepository.save(customerExt);
    }

    @Override
    public void deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
    }
}
