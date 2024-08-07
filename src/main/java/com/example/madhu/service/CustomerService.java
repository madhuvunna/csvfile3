package com.example.madhu.service;

import java.io.PrintWriter;
import java.util.List;

import com.example.madhu.entity.Customer;

public interface CustomerService {
	//public List<Customer> findAll();

	public Customer findById(int id);

	public void save(Customer customer);
	
	public void deleteById(int id);

	public void CsvFile(PrintWriter printwriter);
}
