package io.github.mac9p.shopapp.Controllers;

import io.github.mac9p.shopapp.Model.Customer;
import io.github.mac9p.shopapp.Repositories.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    private List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
}
