package com.Delivery.UsExpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.Delivery.UsExpress.entites.MessageResponse;
import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.services.RiderService;

@Controller
public class RiderWebSocketConroller {

    @Autowired RiderService riderService;
	  
	  @MessageMapping("/rider-data")//app/rider-location send from frontend
	  @SendTo("/usexpress/admin/riders-location")
	  public MessageResponse getRiderLocation(Rider rider) {
	  
		  riderService.update(rider);
		  List<Rider> riders = riderService.getAll();
		  
		  MessageResponse messageResponse = new MessageResponse();
		    messageResponse.setDestination("/usexpress/admin/riders-location");
		    messageResponse.setData(riders);
		  
		  return messageResponse;
	  
	  }
	 
    
}
