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

import com.Delivery.UsExpress.entites.Location;
import com.Delivery.UsExpress.services.LocationService;

/**
 * @author Theikdi Sann
 *
 */

@RestController
@RequestMapping("/location")
public class LocationController {
	@Autowired
	LocationService locationService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Location>> getAll() {
		return new ResponseEntity<List<Location>>(locationService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Location> save(@RequestBody Location location) {
		Location cus = locationService.save(location);
		if (cus != null) {
			return new ResponseEntity<Location>(cus, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
