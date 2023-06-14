/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.List;

import com.Delivery.UsExpress.entites.ConfirmDelivery;

/**
 * @author Theikdi Sann
 *
 */
public interface ConfirmDeliverySerivce {

	public List<ConfirmDelivery> getAll();
	public ConfirmDelivery getById(int id);
	public ConfirmDelivery save(ConfirmDelivery data);
	public ConfirmDelivery update(ConfirmDelivery data);
	public boolean delete(ConfirmDelivery data);
}
