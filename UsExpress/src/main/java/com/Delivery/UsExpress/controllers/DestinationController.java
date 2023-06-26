/**
 * 
 */
package com.Delivery.UsExpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Delivery.UsExpress.entites.DestinationAddress;

import com.Delivery.UsExpress.services.DestinationAddressService;


/**
 * @author Acer
 *
 */
@RestController
@RequestMapping("/destination")
public class DestinationController {
	@Autowired
	DestinationAddressService destinationService;
	
	@GetMapping("/")
	public ResponseEntity<List<DestinationAddress>> getAll() {
		return new ResponseEntity<List<DestinationAddress>>(destinationService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DestinationAddress> save(@RequestBody DestinationAddress customer) {
		DestinationAddress cus = destinationService.save(customer);
		if (cus != null) {
			return new ResponseEntity<DestinationAddress>(cus, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		return destinationService.delete(id);
		
	}

}





