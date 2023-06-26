/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.DestinationAddress;



/**
 * @author Acer
 *
 */
public interface DestinationAddressService {
	
	public DestinationAddress save(DestinationAddress destinationaddress);
	public List<DestinationAddress> getAll();
	public String delete(int id);

}
