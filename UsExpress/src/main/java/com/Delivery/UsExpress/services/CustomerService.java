/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.Customer;

/**
 * @author Theikdi Sann
 *
 */
public interface CustomerService {

	public List<Customer> getAll();
	public Customer getById(int id);
	public Customer save(Customer customer);
	public Customer update(Customer customer);
	public boolean delete(Customer student);
}
