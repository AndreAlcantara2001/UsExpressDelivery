/**
 * 
 */
package com.Delivery.UsExpress.entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryId;
	
	
	@OneToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name = "request_deli_id" ,referencedColumnName = "requestId")
	private RequestDelivery requestDelivery;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "rider_id", referencedColumnName = "id")
	private Rider rider;

/* 	riderId = one or more CDID (rider onetomany ConfirmDelivery)
	many CDID = one riderId			(ConfirmDelivery many to one rider) */

	
}
