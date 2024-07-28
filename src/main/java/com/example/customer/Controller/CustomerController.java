package com.example.customer.Controller;

import com.example.customer.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
@PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer added";
    }
    @PutMapping("/update/{index}")
    public String updateCustomer(@PathVariable int index,@RequestBody Customer customer) {
        customers.set(index, customer);
    return "Customer updated";
    }
    @PutMapping("/deposit/{index}/{amount}")
    public String depositMoney(@PathVariable int index, @PathVariable int amount) {
        if (index >= 0 && index < customers.size()) {
            customers.get(index).setBalance(customers.get(index).getBalance() + amount);
            return "Deposited $" + amount + " to customer at index " + index;
        } else {
            return "Customer not found";
        }
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public String withdrawMoney(@PathVariable int index, @PathVariable int amount) {
        if (index >= 0 && index < customers.size()) {
            if (customers.get(index).getBalance() >= amount) {
                customers.get(index).setBalance(customers.get(index).getBalance() - amount);
                return "Withdrew $" + amount + " from customer at index " + index;
            } else {
                return "Insufficient balance";
            }
        } else {
            return "Customer not found";
        }
    }
    @DeleteMapping("/delete/{index}")
    public String deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return "Customer deleted";
    }



}
