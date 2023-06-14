/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.ConfirmDelivery;
import com.Delivery.UsExpress.repositories.ConfirmDeliveryRepo;

/**
 * @author Theikdi Sann
 *
 */

@Service
public class ConfirmDeliveryServiceImpl implements ConfirmDeliverySerivce{
	
	@Autowired
	ConfirmDeliveryRepo confirmDeliRepo;

	@Override
	public List<ConfirmDelivery> getAll() {
		// TODO Auto-generated method stub
		return confirmDeliRepo.findAll();
	}

	@Override
	public ConfirmDelivery getById(int id) {
		// TODO Auto-generated method stub
		return confirmDeliRepo.findById(id).orElse(null);
	}

	@Override
	public ConfirmDelivery save(ConfirmDelivery data) {
		// TODO Auto-generated method stub
		return confirmDeliRepo.save(data);
	}

	@Override
	public ConfirmDelivery update(ConfirmDelivery data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(ConfirmDelivery data) {
		// TODO Auto-generated method stub
		return false;
	}

}
