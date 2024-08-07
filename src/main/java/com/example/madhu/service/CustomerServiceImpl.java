package com.example.madhu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.madhu.dao.CustomerRepository;
import com.example.madhu.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerrepository;

//	public List<Customer> findAll() {
//		return customerrepository.findAll();
//	}
//	
	@Override
	public void CsvFile(PrintWriter printwriter) 
	{
		List<Customer> customer = customerrepository.findAll();
		try (CSVPrinter csvprinter = new CSVPrinter(printwriter,CSVFormat.DEFAULT)){
			for (Customer customer1 : customer) {
				csvprinter.printRecord(customer1.getId(),customer1.getFirstName(),customer1.getLastName()
//					customer1.getStreetaddress(),customer1.getStreetaddressline(),
//					customer1.getCity(),
//					customer1.getState(),customer1.getPostal(),customer1.getPhonenumber(),customer1.getEmail(),
//					customer1.getHowdidyouhereaboutus(),customer1.getFeedbackaboutus(),
//					customer1.getSuggestionsifanyforfurtherimprovement(),
//					customer1.getWillyoubewillingtorecommendus()
						);
		}
		
		}
			catch (IOException e) {
			  e.printStackTrace();
		}
	}
	
		
		@Override
	public Customer findById(int id) {
		Customer customer = null;
		try {

			customer = customerrepository.findById(id).get();

		} catch (Exception e) {
			new RuntimeException("Id not find.");
		}

		return customer;
	}

	@Override
	public void save(Customer customer) {
		customerrepository.save(customer);

	}

	@Override
	public void deleteById(int id) {
		customerrepository.deleteById(id);
	}

	
}
