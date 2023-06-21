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

import com.Delivery.UsExpress.entites.ConfirmDelivery;
import com.Delivery.UsExpress.services.ConfirmDeliverySerivce;

/**
 * @author Theikdi Sann
 *
 */

@RestController
@RequestMapping("/cDelivery")
public class ConfirmDeliveryController {
	@Autowired
	ConfirmDeliverySerivce cDService;
	
	@GetMapping("/")
	public ResponseEntity<List<ConfirmDelivery>> getAll() {
		return new ResponseEntity<List<ConfirmDelivery>>(cDService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfirmDelivery> save(@RequestBody ConfirmDelivery customer) {
		ConfirmDelivery cus = cDService.save(customer);
		if (cus != null) {
			return new ResponseEntity<ConfirmDelivery>(cus, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
