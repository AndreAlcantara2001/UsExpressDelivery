/**
 * 
 */
package com.Delivery.UsExpress.controllers.riderControllersPKG;

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

/**
 * @author Acer
 *
 */

@RestController
@RequestMapping("/rider")
public class RiderController {

	@Autowired
	RiderService riderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Rider>> getAll(){
		List<Rider> riders = riderService.getAll();
		
		if(riders.size() >= 1) {
			return new ResponseEntity<List<Rider>>(riders, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Rider> save(@RequestBody Rider rider){
		Rider saveRider = riderService.save(rider);
		if(saveRider.equals(null)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Rider>(saveRider,HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping(value = "/offline-status", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setStatusOffline(@RequestBody Rider rider) {
		riderService.updateStatusOffline(rider);
		System.out.println("Offline rider"+rider.getId());
	}
}
