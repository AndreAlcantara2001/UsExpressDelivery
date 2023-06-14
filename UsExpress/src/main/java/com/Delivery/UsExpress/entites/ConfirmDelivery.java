/**
 * 
 */
package com.Delivery.UsExpress.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Theikdi Sann
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ConfirmDelivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deliveryId;
	
	private double weight;
	private double distance;
	private double cost;
	private String receviverName;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "location_id", referencedColumnName = "locationId")
	private Location location;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;
}
