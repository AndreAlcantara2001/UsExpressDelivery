/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.Location;

/**
 * @author Theikdi Sann
 *
 */
public interface LocationService {

	public List<Location> getAll();
	public Location getById(int id);
	public Location save(Location location);
	public Location update(Location location);
	public boolean delete(Location location);
}
