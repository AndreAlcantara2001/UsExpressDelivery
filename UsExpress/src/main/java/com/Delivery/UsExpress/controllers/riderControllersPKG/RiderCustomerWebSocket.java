/**
 * 
 */
package com.Delivery.UsExpress.controllers.riderControllersPKG;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.Delivery.UsExpress.entites.PickUpAddress;
import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.entites.RiderLocationMessageResponse;
import com.Delivery.UsExpress.services.RiderFilter;
import com.Delivery.UsExpress.services.RiderService;

/**
 * @author Acer
 *
 */

@Controller
public class RiderCustomerWebSocket {

	//filterRiders = new ArrayList<>();

	@Autowired
	RiderService riderService;

	@MessageMapping("/send-find-near-riders")
	@SendTo("/usexpress/customer/receive-find-near-riders")
	public RiderLocationMessageResponse findNearRiders(PickUpAddress pickupaddress) {

		List<Rider> riders = riderService.getAll();

		RiderFilter riderFilter = new RiderFilter();

		List<Rider>  filterRiders = riderFilter.findNearRiders(riders, pickupaddress);

		//if (filterRiders.size() >= 1) {
			RiderLocationMessageResponse riderLocationMessageResponse = new RiderLocationMessageResponse();
			riderLocationMessageResponse.setDestination("/usexpress/customer/receive-find-near-riders");
			riderLocationMessageResponse.setData(filterRiders);
			
			System.out.println("controller works");

			return riderLocationMessageResponse;
		//} else {
			//return null;
		//}

	}

}
