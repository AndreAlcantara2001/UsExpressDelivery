/**
 * 
 */
package com.Delivery.UsExpress.controllers;

import java.util.ArrayList;
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

import com.Delivery.UsExpress.entites.PickUpAddress;
import com.Delivery.UsExpress.entites.RequestDelivery;
import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.services.RequestDeliveryService;
import com.Delivery.UsExpress.services.RiderFilter;
import com.Delivery.UsExpress.services.RiderService;



/**
 * @author Acer
 *
 */

@RestController
@RequestMapping("/requestdelivery")
public class RequestDeliveryController {
	
	@Autowired
	RequestDeliveryService reqService;
	
	@Autowired
	RiderService riderService;

	List<Rider> filterRiders = new ArrayList<>();	
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestDelivery> save(@RequestBody RequestDelivery reqDeli) {
		RequestDelivery req = reqService.save(reqDeli);
		if (req != null) {
			return new ResponseEntity<RequestDelivery>(req, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RequestDelivery>> getAll() {
		return new ResponseEntity<List<RequestDelivery>>(reqService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/find-near-riders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rider>> findNearRiders(@RequestBody PickUpAddress pickupaddress){
		
		List<Rider> riders = riderService.getAll();
		
		RiderFilter riderFilter = new RiderFilter();
		
		filterRiders = riderFilter.findNearRiders(riders, pickupaddress);
		
		System.out.println(filterRiders);
		
		if(filterRiders.size() >= 1) {
			return new ResponseEntity<List<Rider>>(filterRiders,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
			
	}

}
