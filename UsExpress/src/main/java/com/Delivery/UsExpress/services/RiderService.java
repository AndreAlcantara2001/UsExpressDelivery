/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.Rider;

/**
 * @author Acer
 *
 */
public interface RiderService {
	
	public void updateLocation(Rider rider);

	public Rider getById(int id);

	public String deleteRider(Rider rider);
	
	public Rider save(Rider rider);

	public List<Rider> getAll();
	
	public void updateStatusOffline(Rider rider);
}
