/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Delivery.UsExpress.entites.Rider;
import com.Delivery.UsExpress.repositories.RiderRepo;

/**
 * @author Acer
 *
 */

@Service
public class RiderServiceImpl implements RiderService {

	@Autowired
	RiderRepo riderRepo;

	@Override
	public void updateLocation(Rider rider) {
		// TODO Auto-generated method stub
		Rider updateRider = getById(rider.getId());
		updateRider.setStatus(rider.getStatus());
		updateRider.setLatitude(rider.getLatitude());
		updateRider.setLongitude(rider.getLongitude());
		save(updateRider);
	}

	@Override
	public Rider getById(int id) {
		// TODO Auto-generated method stub
		return riderRepo.findById(id).orElse(null);
	}

	@Override
	public String deleteRider(Rider rider) {
		// TODO Auto-generated method stub
		Rider delRider = riderRepo.findById(rider.getId()).orElse(null);
		if (delRider.equals(null)) {
			return "can not delete";
		} else {
			riderRepo.delete(delRider);
			return "rider" + rider.getId() + "is deleted";
		}
	}

	@Override
	public Rider save(Rider rider) {
		// TODO Auto-generated method stub
		return riderRepo.save(rider);
	}

	@Override
	public List<Rider> getAll() {
		// TODO Auto-generated method stub
		return riderRepo.findAll();
	}

	@Override
	public void updateStatusOffline(Rider rider) {
		// TODO Auto-generated method stub
		Rider updateRider = getById(rider.getId());
		
		updateRider.setStatus(rider.getStatus());
		
		save(updateRider);
	}

}
