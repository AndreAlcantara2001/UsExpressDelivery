/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.Location;
import com.Delivery.UsExpress.repositories.LocationRepo;

/**
 * @author Theikdi Sann
 *
 */

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	LocationRepo locationRepo;

	@Override
	public List<Location> getAll() {
		// TODO Auto-generated method stub
		return locationRepo.findAll();
	}

	@Override
	public Location getById(int id) {
		// TODO Auto-generated method stub
		return locationRepo.findById(id).orElse(null);
	}

	@Override
	public Location save(Location location) {
		// TODO Auto-generated method stub
		return locationRepo.save(location);
	}

	@Override
	public Location update(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Location location) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
