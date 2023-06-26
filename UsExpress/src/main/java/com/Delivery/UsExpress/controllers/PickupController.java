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

import com.Delivery.UsExpress.entites.PickUpAddress;

import com.Delivery.UsExpress.services.PickUpAdressService;

/**
 * @author Acer
 *
 */
@RestController
@RequestMapping("/pickup")
public class PickupController {

	@Autowired
	PickUpAdressService pickupService;
	
	@GetMapping("/")
	public ResponseEntity<List<PickUpAddress>> getAll() {
		return new ResponseEntity<List<PickUpAddress>>(pickupService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PickUpAddress> save(@RequestBody PickUpAddress customer) {
		PickUpAddress cus = pickupService.save(customer);
		if (cus != null) {
			return new ResponseEntity<PickUpAddress>(cus, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		return pickupService.delete(id);
		
	}
}
