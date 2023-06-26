/**
 * 
 */
package com.Delivery.UsExpress.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Acer
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PickUpAddress {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int pickUpAddressId;
		private double latitude;
		private double longitude;
		
		@JsonIgnore
		@OneToOne(mappedBy = "pickupaddress")
		private RequestDelivery requestDelivery;
		
	
}
