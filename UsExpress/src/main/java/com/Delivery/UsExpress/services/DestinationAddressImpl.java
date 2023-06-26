/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.DestinationAddress;

import com.Delivery.UsExpress.repositories.DestinationAddressRepo;


/**
 * @author Acer
 *
 */
@Service
public class DestinationAddressImpl implements DestinationAddressService{




	@Autowired
	DestinationAddressRepo destinationRepo;
	
	@Override
	public DestinationAddress save(DestinationAddress destination) {
		// TODO Auto-generated method stub
		return destinationRepo.save(destination);
	}

	@Override
	public List<DestinationAddress> getAll() {
		// TODO Auto-generated method stub
		return destinationRepo.findAll();
	}
	
	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		DestinationAddress category =  destinationRepo.findById(id).orElse(null);
		if (category == null) {
			return "ID invalid";
		}else {
			destinationRepo.delete(category);
			return "Pickup Address " + id + " is Deleted";
		}
	}
}
