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

import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.services.RiderService;

@RestController
@RequestMapping("/rider")
public class RiderRestController {
	
	@Autowired
	RiderService riderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Rider>> getAllRiders(){
		List<Rider> riders = riderService.getAll();
		
		if(riders.size() >= 1) {
			return new ResponseEntity<List<Rider>>(riders, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rider> addRider(@RequestBody Rider rider){
		Rider newRider = riderService.saveRider(rider);
		
		if(!(newRider.equals(null))) {
			return new ResponseEntity<Rider>(newRider,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
}
