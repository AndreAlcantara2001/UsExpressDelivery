/**
 * 
 */
package com.Delivery.UsExpress.controllers.riderControllersPKG;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.entites.RiderLocationMessageResponse;
import com.Delivery.UsExpress.entites.Status;
import com.Delivery.UsExpress.services.RiderService;




/**
 * @author Acer
 *
 */
@Controller
public class RiderAdminWebSocketController {
	
	
	  @Autowired
	 RiderService riderService;
	  
	  @MessageMapping("/rider-data")//app/rider-location send from frontend
	  @SendTo("/usexpress/admin/riders-location")
	  public RiderLocationMessageResponse getRiderLocation(Rider rider) {
	  	  
		  riderService.updateLocation(rider);
		  List<Rider> riders = riderService.getAll();
		  
		 List<Rider> onlineRiders = riders.stream().filter(r -> r.getStatus().equals(Status.ONLINE)).collect(Collectors.toList());
		 
		 System.out.println(onlineRiders);
		 
		  RiderLocationMessageResponse messageResponse = new RiderLocationMessageResponse();
		    messageResponse.setDestination("/usexpress/admin/riders-location");
		    messageResponse.setData(onlineRiders);
		  
		  return messageResponse;
	  
	  }
	 
	
	
}

//@SendTo("/topic/riderslocation")//app/ sendfrom frontend
