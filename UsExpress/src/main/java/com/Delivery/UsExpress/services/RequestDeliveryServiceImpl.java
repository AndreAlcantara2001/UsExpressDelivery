/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

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

	@Override
	public List<RequestDelivery> getAll() {
		// TODO Auto-generated method stub
		return reqDeliRepo.findAll();
	}

	@Override
	public RequestDelivery getById(int id) {
		// TODO Auto-generated method stub
		return reqDeliRepo.findById(id).orElse(null);
	}

}
