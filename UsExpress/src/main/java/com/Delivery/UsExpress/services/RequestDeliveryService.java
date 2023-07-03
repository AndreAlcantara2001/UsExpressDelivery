/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.RequestDelivery;

/**
 * @author Acer
 *
 */
public interface RequestDeliveryService {
	
	public RequestDelivery getById(int id);
	public RequestDelivery save(RequestDelivery reqDeli);
	public List<RequestDelivery> getAll();

}
