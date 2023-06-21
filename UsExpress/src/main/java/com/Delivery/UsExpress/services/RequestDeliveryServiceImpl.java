/**
 * 
 */
package com.Delivery.UsExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.RequestDelivery;
import com.Delivery.UsExpress.repositories.RequestDeliveryRepo;

/**
 * @author Acer
 *
 */

@Service
public class RequestDeliveryServiceImpl implements RequestDeliveryService{
	
	@Autowired
	RequestDeliveryRepo reqDeliRepo;
	
	@Override
	public RequestDelivery save(RequestDelivery reqDeli) {
		// TODO Auto-generated method stub
		return reqDeliRepo.save(reqDeli);
	}

}
