package net.alexandrepaulkouame.web;

import jakarta.validation.Valid;
import net.alexandrepaulkouame.dto.CustomerDTO;
import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.services.CustomerService;
import net.alexandrepaulkouame.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class CustomerGraphlController {

    @Autowired
    private  CustomerService customerService;

    @Autowired
    private RoleService roleService;

    public CustomerGraphlController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public Customer getCustomerById(@Argument UUID id){
        return customerService.getCustmerById(id);
    }

    @MutationMapping
    public Customer createCustomer(@Argument CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }

    @MutationMapping
    public Customer updateCustomer(@Argument UUID id, @Argument CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @MutationMapping
    public Boolean deleteCustomerById(@Argument UUID id){
        customerService.deleteCustomerById(id);
        return true;
    }
}
