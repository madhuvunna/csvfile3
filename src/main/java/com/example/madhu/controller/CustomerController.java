package com.example.madhu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.madhu.entity.Customer;
import com.example.madhu.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private CustomerService customerservice;

//	@GetMapping("/customer")
//	public List<Customer> customer() {
//
//		List<Customer> employees = customerservice.findAll();
//
//		return employees;
//	}
	
	@RequestMapping("/customer")	
	public void CsvFile(HttpServletResponse response) throws IOException {
	response.setContentType("text/csv");
	response.setHeader("Content-Disposition", "attachement; filename=\"customer.csv\"");
	customerservice.CsvFile(response.getWriter());
	}
		
	
	@GetMapping("customer/{custid}")

	public Customer custbyid(@PathVariable int custid) {
		Customer customer = customerservice.findById(custid);
		if (customer == null) {
			throw new RuntimeException("Customer id Not Found :" + custid);
		}

		return customer;
	}

	@PostMapping("/customer")
	public Customer save(@RequestBody Customer customer) {
		customer.setId(0);
		//System.out.println(customer);
		customerservice.save(customer);
		return customer;
	}

	@PutMapping("/customer")
	public void update(@RequestBody Customer customer) {
		customerservice.save(customer);

	}

	@DeleteMapping("/customer/{custid}")
	public String deleteCustomer(@PathVariable int custid) {

		Customer customer = customerservice.findById(custid);

		if (customer == null) {
			throw new RuntimeException("Customer Not Found " + custid);
		}

		customerservice.deleteById(custid);

		return "Deleted Customer :" + custid;
	}
}
	

//	@RequestMapping(value = "/customer", method = RequestMethod.POST)
//	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
//		customer = customerservice.createCustomer(customer);
//      return new ResponseEntity<>("Customer is created successfully with Id = " + customer.getId(),
//				HttpStatus.CREATED);
//	}

