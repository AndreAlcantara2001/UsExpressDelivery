/**
 * 
 */
package com.Delivery.UsExpress.controllers.riderControllersPKG;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.entites.RiderLocationMessageResponse;
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
	  
		  riderService.update(rider);
		  List<Rider> riders = riderService.getAll();
		  
		  RiderLocationMessageResponse messageResponse = new RiderLocationMessageResponse();
		    messageResponse.setDestination("/usexpress/admin/riders-location");
		    messageResponse.setData(riders);
		  
		  return messageResponse;
	  
	  }
	 
	
	
}

//@SendTo("/topic/riderslocation")//app/ sendfrom frontend
