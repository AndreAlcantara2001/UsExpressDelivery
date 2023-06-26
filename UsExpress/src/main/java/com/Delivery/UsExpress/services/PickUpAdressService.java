/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.PickUpAddress;


/**
 * @author Acer
 *
 */
public interface PickUpAdressService {
	
	public PickUpAddress save(PickUpAddress pickupaddress);
	public List<PickUpAddress> getAll();
	public String delete(int id);

}
