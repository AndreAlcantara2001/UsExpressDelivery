/**
 * 
 */
package com.Delivery.UsExpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Delivery.UsExpress.entites.Customer;
import com.Delivery.UsExpress.services.CustomerService;

/**
 * @author Theikdi Sann
 *
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAll() {
		return new ResponseEntity<List<Customer>>(customerService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		Customer cus = customerService.save(customer);
		if (cus != null) {
			return new ResponseEntity<Customer>(cus, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
