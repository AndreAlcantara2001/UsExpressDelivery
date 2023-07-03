/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.PickUpAddress;

import com.Delivery.UsExpress.repositories.PickUpAddressRepo;



/**
 * @author Acer
 *
 */

@Service
public class PickUpImpl implements PickUpAdressService{

	@Autowired
	PickUpAddressRepo pickupRepo;
	
	@Override
	public PickUpAddress save(PickUpAddress reqDeli) {
		// TODO Auto-generated method stub
		return pickupRepo.save(reqDeli);
	}

	@Override
	public List<PickUpAddress> getAll() {
		// TODO Auto-generated method stub
		return pickupRepo.findAll();
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		PickUpAddress category =  pickupRepo.findById(id).orElse(null);
		if (category == null) {
			return "ID invalid";
		}else {
			pickupRepo.delete(category);
			return "Pickup Address " + id + " is Deleted";
		}
	}

	@Override
	public PickUpAddress getById(int id) {
		// TODO Auto-generated method stub
		return pickupRepo.findById(id).orElse(null);
	}
	

}
