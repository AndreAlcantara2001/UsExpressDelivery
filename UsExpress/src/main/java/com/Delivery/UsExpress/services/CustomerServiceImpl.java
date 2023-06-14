/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.Customer;
import com.Delivery.UsExpress.repositories.CustomerRepo;

/**
 * @author Theikdi Sann
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return customerRepo.findById(id).orElse(null);
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepo.save(customer);
	}

	@Override
	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Customer student) {
		// TODO Auto-generated method stub
		return false;
	}

}
