/**
 * 
 */
package com.Delivery.UsExpress.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Delivery.UsExpress.entites.PickUpAddress;
import com.Delivery.UsExpress.entites.RequestDelivery;
import com.Delivery.UsExpress.entites.ResponseReqDeli;
import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.entites.Status;
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
	SimpMessagingTemplate simpMessagingTemplate;

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

	@GetMapping("/{id}")
	public ResponseEntity<PickUpAddress> getByIDForPickUpAddress(@PathVariable int id) {

		RequestDelivery reqDeli = reqService.getById(id);

		PickUpAddress pickupaddress = reqDeli.getPickupaddress();

		if (!(pickupaddress.equals(null))) {
			return new ResponseEntity<PickUpAddress>(pickupaddress, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	/*
	 * @PostMapping(value = "/find-near-riders", consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<List<Rider>>
	 * findNearRiders(@RequestBody PickUpAddress pickupaddress){
	 * 
	 * List<Rider> riders = riderService.getAll();
	 * 
	 * RiderFilter riderFilter = new RiderFilter();
	 * 
	 * filterRiders = riderFilter.findNearRiders(riders, pickupaddress);
	 * 
	 * System.out.println(filterRiders);
	 * 
	 * if(filterRiders.size() >= 1) { return new
	 * ResponseEntity<List<Rider>>(filterRiders,HttpStatus.OK); }else { return new
	 * ResponseEntity<>(null, HttpStatus.BAD_REQUEST); }
	 * 
	 * }
	 */

	/*
	 * @PostMapping(value = "/customer/save-request-delivery", consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<ResponseReqDeli>
	 * saveReqDeli(@RequestBody RequestDelivery reqDeli) {
	 * 
	 * RequestDelivery newReqDeli = reqService.save(reqDeli); if
	 * (!(newReqDeli.equals(null))) {
	 * 
	 * PickUpAddress pickupaddress = newReqDeli.getPickupaddress();
	 * 
	 * System.out.println("PickUpAddress: " + pickupaddress);
	 * 
	 * List<Rider> riders = riderService.getAll();
	 * 
	 * RiderFilter riderFilter = new RiderFilter();
	 * 
	 * List<Rider> filteredRiders = riderFilter.findNearRiders(riders,
	 * pickupaddress);
	 * 
	 * //[rider1,rider2,rider3]
	 * 
	 * List<Rider> onlineFilteredRiders = filteredRiders.stream().filter(r ->
	 * r.getStatus().equals(Status.ONLINE) ).collect(Collectors.toList());
	 * 
	 * ResponseReqDeli respReqDeli = new ResponseReqDeli();
	 * respReqDeli.setFilterRiders(onlineFilteredRiders);
	 * respReqDeli.setReqDeliId(newReqDeli.getRequestId());
	 * 
	 * for (Rider rider : filteredRiders) { sendRequestDeliToRider(rider.getId(),
	 * newReqDeli); }
	 * 
	 * return new ResponseEntity<ResponseReqDeli>(respReqDeli, HttpStatus.ACCEPTED);
	 * 
	 * } else { return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); }
	 * 
	 * }
	 */



	@PostMapping(value = "/customer/save-request-delivery", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseReqDeli> saveReqDeli(@RequestBody RequestDelivery reqDeli) {

		RequestDelivery newReqDeli = reqService.save(reqDeli);
		if (!(newReqDeli.equals(null))) {

			PickUpAddress pickupaddress = newReqDeli.getPickupaddress();

			System.out.println("PickUpAddress: " + pickupaddress);

			List<Rider> riders = riderService.getAll();

			RiderFilter riderFilter = new RiderFilter();

			List<Rider> filteredRiders = riderFilter.findNearRiders(riders, pickupaddress);

			// [rider1,rider2,rider3]

			//Response HTTP
			List<Rider> onlineFilteredRiders = filteredRiders.stream().filter(r -> r.getStatus().equals(Status.ONLINE))
					.collect(Collectors.toList());

			ResponseReqDeli respReqDeli = new ResponseReqDeli();
			respReqDeli.setFilterRiders(onlineFilteredRiders);
			respReqDeli.setReqDeliId(newReqDeli.getRequestId());

			//send to rider WS
			for (Rider rider : onlineFilteredRiders) {
				sendRequestDeliToRider(rider.getId(), newReqDeli);
			}
			
			//send to customer WS
			int customerId = newReqDeli.getCustomer().getCustomerId();
			sendFilteredRidersToCustomer(customerId, onlineFilteredRiders);

			return new ResponseEntity<ResponseReqDeli>(respReqDeli, HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		//

	}
	
	public void sendRequestDeliToRider(int riderId, RequestDelivery newReqDeli) {
		System.out.println(riderId + "is send requestDelivery");
		String destinationUrl = "/usexpress/rider/delivery-request/" + riderId;
		simpMessagingTemplate.convertAndSend(destinationUrl, newReqDeli);
	}
	
	public void sendFilteredRidersToCustomer(int customerId, List<Rider> onlineFilteredRiders) {
		System.out.println(customerId + "is send filtered riders");
		String destinationUrl = "/usexpress/customer/near-riders/" + customerId;
		simpMessagingTemplate.convertAndSend(destinationUrl, onlineFilteredRiders);
	}

}
