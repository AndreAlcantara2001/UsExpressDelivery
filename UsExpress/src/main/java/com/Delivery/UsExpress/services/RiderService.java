package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.Rider;

public interface RiderService {
    	public void update(Rider rider);

	public Rider getById(int id);

	public String deleteRider(Rider rider);
	
	public Rider saveRider(Rider rider);

	public List<Rider> getAll();
}
